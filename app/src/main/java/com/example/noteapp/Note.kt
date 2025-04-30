package com.example.noteapp

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Long,
    val title: String = "Title",
    val content: String = "Text",
    val color: Color = Color.White
)

val dummyNotes = listOf(
    Note(
        id = 1L,
        title = "Shopping List",
        content = "Milk, Eggs, Bread, Butter",
        color = Color(0xFFFFF59D) // light yellow
    ),
    Note(
        id = 2L,
        title = "Workout Plan",
        content = "Mon: Chest, Tue: Back, Wed: Legs",
        color = Color(0xFF80DEEA) // light blue
    ),
    Note(
        id = 3L,
        title = "Meeting Notes",
        content = "Discuss UI redesign and deadlines",
        color = Color(0xFFB39DDB) // lavender
    ),
    Note(
        id = 4L,
        title = "Ideas",
        content = "Build a mood tracker app with Kotlin",
        color = Color(0xFFA5D6A7) // light green
    ),
    Note(
        id = 5L,
        title = "Reminder",
        content = "Call dentist on Friday",
        color = Color(0xFFFFAB91) // soft orange
    )
)
