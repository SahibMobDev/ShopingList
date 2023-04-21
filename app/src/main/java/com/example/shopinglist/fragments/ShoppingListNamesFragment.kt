package com.example.shopinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopinglist.activities.MainApp
import com.example.shopinglist.databinding.FragmentShoppingListNamesBinding
import com.example.shopinglist.db.MainViewModel
import com.example.shopinglist.db.ShopListNameAdapter
import com.example.shopinglist.dialogs.NewListDialog
import com.example.shopinglist.entities.ShoppingListName
import com.example.shopinglist.utils.TimeManager

class ShoppingListNamesFragment : BaseFragment() {
    private lateinit var binding: FragmentShoppingListNamesBinding
    private lateinit var adapter: ShopListNameAdapter
    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        NewListDialog.showDialog(activity as AppCompatActivity, object : NewListDialog.Listener {
            override fun onClick(name: String) {
                val shopListName = ShoppingListName(
                    id = null,
                    name = name,
                    time = TimeManager.getCurrentTime(),
                    allItemCounter = 0,
                    checkedItemCounter = 0,
                    itemsIds = ""
                )
                mainViewModel.insertShopListName(shopListName)
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoppingListNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding){
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = ShopListNameAdapter()
        rcView.adapter = adapter
    }

    private fun observer() {
        mainViewModel.allShopListNames.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = ShoppingListNamesFragment()
    }

}