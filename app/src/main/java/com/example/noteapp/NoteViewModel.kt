package com.example.noteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NoteViewModel: ViewModel() {
    private val noteRepository = Graph.getNoteRepository()

    private val _notes: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notesList: StateFlow<List<Note>> = _notes

    init {
        getAllNotes()
    }

    val colorsList = listOf(
        R.color.peach, R.color.lavender, R.color.baby_pink,
        R.color.powder_blue, R.color.beige_sand, R.color.light_yellow,
        R.color.mint_green, R.color.pale_cyan, R.color.soft_coral
    )

    private var _nextId = 1L

    private fun getAllNotes() {
        noteRepository.getAllNotes().onEach { notesList ->
            _notes.value = notesList
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.AddNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    noteRepository.addANote(Note())
                    _nextId ++
                }
            }
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    noteRepository.deleteANote(event.id)
                }
            }
            is NoteEvent.UpdateNote -> {
                viewModelScope.launch(Dispatchers.IO) {
                    noteRepository.updateANote(event.id, event.title, event.content, event.color)
                }
            }
        }
    }
}