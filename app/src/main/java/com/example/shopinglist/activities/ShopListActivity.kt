package com.example.shopinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.shopinglist.databinding.ActivityShopListBinding
import com.example.shopinglist.db.MainViewModel
import com.example.shopinglist.entities.ShopListNameItem

class ShopListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopListBinding
    private var shopListNameItem: ShopListNameItem? = null

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
        binding.tvTest.text = shopListNameItem?.name
    }

    companion object {
        const val SHOP_LIST_NAME = "shop_list_name"
    }
}