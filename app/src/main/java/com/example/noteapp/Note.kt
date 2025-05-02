package com.example.noteapp

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Long,
    val title: String = "Title",
    val content: String = "Text",
    val color: Int = R.color.white
)
