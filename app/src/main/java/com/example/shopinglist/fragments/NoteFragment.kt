package com.example.shopinglist.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.shopinglist.R
import com.example.shopinglist.activities.MainApp
import com.example.shopinglist.activities.NewNoteActivity
import com.example.shopinglist.databinding.FragmentNoteBinding
import com.example.shopinglist.db.MainViewModel

class NoteFragment : BaseFragment() {
    private lateinit var binding: FragmentNoteBinding
    private lateinit var editLauncher: ActivityResultLauncher<Intent>
    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
    }

    override fun onClickNew() {
        editLauncher.launch(Intent(activity, NewNoteActivity::class.java))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onEditResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun onEditResult() {
        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                Log.d("MyLog", "title: ${it.data?.getStringExtra(TITLE_KEY)}")
                Log.d("MyLog", "description: ${it.data?.getStringExtra(DESK_KEY)}")
            }
        }
    }

    companion object {
        const val TITLE_KEY = "title_key"
        const val DESK_KEY = "desc_key"
        @JvmStatic
        fun newInstance() = NoteFragment()
    }
}