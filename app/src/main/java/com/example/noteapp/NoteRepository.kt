package com.example.noteapp

import kotlinx.coroutines.flow.Flow

class NoteRepository(
    private val noteDao: NoteDao
) {
    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun addANote(note: Note) {
        noteDao.addANote(note)
    }

    suspend fun updateANote(id: Long, title: String, content: String, color: Int) {
        noteDao.updateANote(id, title, content, color)
    }

    suspend fun deleteANote(id: Long) {
        noteDao.deleteANote(id)
    }
}