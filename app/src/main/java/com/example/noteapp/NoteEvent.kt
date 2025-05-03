package com.example.noteapp

sealed class NoteEvent {
    object AddNote: NoteEvent()
    data class DeleteNote(val id: Long): NoteEvent()
    data class UpdateNote(val id: Long, val title: String, val content: String, val color: Int): NoteEvent()
}