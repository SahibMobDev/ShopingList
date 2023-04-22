package com.example.shopinglist.db

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import com.example.shopinglist.entities.NoteItem
import com.example.shopinglist.entities.ShopListNameItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM note_list")
    fun getAllNotes() : Flow<List<NoteItem>>

    @Query("SELECT * FROM shopping_list_names")
    fun getAllShopListNames() : Flow<List<ShopListNameItem>>

    @Insert()
    suspend fun insertNote(noteItem: NoteItem)

    @Insert()
    suspend fun insertShopListName(name: ShopListNameItem)

    @Update()
    suspend fun updateNote(noteItem: NoteItem)

    @Update()
    suspend fun updateListName(shopListName: ShopListNameItem)

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id:Int)

    @Query("DELETE FROM shopping_list_names WHERE id IS :id")
    suspend fun deleteShopListName(id:Int)
}