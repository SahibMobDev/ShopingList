package com.example.shopinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopinglist.entities.LibraryItem
import com.example.shopinglist.entities.NoteItem
import com.example.shopinglist.entities.ShoppingListItem
import com.example.shopinglist.entities.ShoppingListName

@Database(entities = [LibraryItem::class, NoteItem::class,
        ShoppingListItem::class, ShoppingListName::class], version = 1)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao() : Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "shopping_list_db"
                ).build()
                instance
            }
        }
    }
}