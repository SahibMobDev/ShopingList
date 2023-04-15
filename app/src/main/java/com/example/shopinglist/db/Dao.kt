package com.example.shopinglist.db

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.shopinglist.entities.NoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM note_list")
    fun getAllNotes() : Flow<List<NoteItem>>

    @Insert()
    suspend fun insertNote(noteItem: NoteItem)

    @Update()
    suspend fun updateNote(noteItem: NoteItem)

    @Query("DELETE FROM note_list WHERE id IS :id")
    suspend fun deleteNote(id:Int)
}