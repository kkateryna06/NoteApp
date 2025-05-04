package com.example.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    @ColumnInfo(name = "title") var title: String = "Title",
    @ColumnInfo(name = "content") var content: String = "Text",
    @ColumnInfo(name = "color") var color: Int = R.color.white
    )
