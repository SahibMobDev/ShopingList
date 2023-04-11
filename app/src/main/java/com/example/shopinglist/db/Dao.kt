package com.example.shopinglist.db

import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import com.example.shopinglist.entities.NoteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM note_list")
    fun getAllNotes() : Flow<List<NoteItem>>

    @Insert()
    fun insertNote(noteItem: NoteItem)
}