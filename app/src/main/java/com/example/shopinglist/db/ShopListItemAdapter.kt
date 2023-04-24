package com.example.shopinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist.R
import com.example.shopinglist.databinding.ListNameItemBinding
import com.example.shopinglist.entities.ShopListNameItem
import com.example.shopinglist.entities.ShoppingListItem

class ShopListItemAdapter(private val listener: Listener) : ListAdapter<ShoppingListItem, ShopListItemAdapter.ItemHolder>(ItemComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return if (viewType == 0)
            ItemHolder.createShopItem(parent)
        else
            ItemHolder.createLibraryItem(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        if (getItem(position).itemType == 0) {
            holder.setItemData(getItem(position), listener)
        } else {
            holder.setLibraryData(getItem(position), listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType
    }


    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListNameItemBinding.bind(view)

        fun setItemData(shopListItem: ShoppingListItem, listener: Listener) = with(binding) {

        }

        fun setLibraryData(shopListItem: ShoppingListItem, listener: Listener) = with(binding) {

        }

        companion object {
            fun createShopItem(parent: ViewGroup) : ItemHolder {
                return ItemHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_name_item, parent, false))
            }
            fun createLibraryItem(parent: ViewGroup) : ItemHolder {
                return ItemHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_name_item, parent, false))
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<ShoppingListItem>() {
        override fun areItemsTheSame(
            oldItem: ShoppingListItem,
            newItem: ShoppingListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShoppingListItem,
            newItem: ShoppingListItem
        ): Boolean {
            return oldItem == newItem
        }

    }


    interface Listener {
        fun deleteItem(id: Int)
        fun onClickItem(shopListNameItem: ShopListNameItem)
        fun editItem(shopListName: ShopListNameItem)
    }
}