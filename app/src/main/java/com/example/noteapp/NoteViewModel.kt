package com.example.noteapp

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteViewModel: ViewModel() {
    private val _notesList: MutableStateFlow<List<Note>> = MutableStateFlow(listOf(
        Note(
            id = 1L,
            title = "Shopping List",
            content = "Milk, Eggs, Bread, Butter"
        ),
        Note(
            id = 2L,
            title = "Workout Plan",
            content = "Mon: Chest, Tue: Back, Wed: Legs"
        ),
        Note(
            id = 3L,
            title = "Meeting Notes",
            content = "Discuss UI redesign and deadlines"
        ),
        Note(
            id = 4L,
            title = "Ideas",
            content = "Build a mood tracker app with Kotlin"
        ),
        Note(
            id = 5L,
            title = "Reminder",
            content = "Call dentist on Friday"
        )
    ))
    val notesList: StateFlow<List<Note>> = _notesList

    val colorsList = listOf(
        R.color.peach, R.color.lavender, R.color.baby_pink,
        R.color.powder_blue, R.color.beige_sand, R.color.light_yellow,
        R.color.mint_green, R.color.pale_cyan, R.color.soft_coral
    )

    fun addNote(note: Note) {
        _notesList.value += note
        Log.d("DEBUG", "NoteList: ${notesList.value}")
    }

    fun deleteNote(note: Note) {
        _notesList.value -= note
    }

    fun updateNote(id: Long, title: String, content: String, color: Int) {
        val index = _notesList.value.indexOfFirst { it.id == id }
        val updatedList = _notesList.value.toMutableList()
        updatedList[index] = Note(id, title, content, color)
        _notesList.value = updatedList
    }
}