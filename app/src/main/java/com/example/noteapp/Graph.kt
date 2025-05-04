package com.example.noteapp

import android.content.Context
import androidx.room.Room

object Graph {
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var noteRepository: NoteRepository

    fun provide(context: Context) {
        noteDatabase = Room.databaseBuilder(
            context = context.applicationContext,
            klass = NoteDatabase::class.java,
            name = "notes.db"
        ).build()

        noteDao = noteDatabase.noteDao()
        noteRepository = NoteRepository(noteDao)
    }

    fun getNoteRepository(): NoteRepository {
        return noteRepository
    }
}