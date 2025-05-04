package com.example.noteapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    // get all notes
    @Query("select * from notes_table")
    fun getAllNotes(): Flow<List<Note>>

    // add new note
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addANote(note: Note)

    // update a note
    @Query("update notes_table set title= :title, content = :content, color = :color where id == :id")
    fun updateANote(id: Long, title: String, content: String, color: Int)

    // delete a note
    @Query("delete from notes_table where id == :id")
    fun deleteANote(id: Long)
}