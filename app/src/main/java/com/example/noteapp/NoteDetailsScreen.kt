package com.example.noteapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun NoteDetailsScreen(navController: NavController, id: Long) {
    val note = dummyNotes[(id - 1L).toInt()]
    val title = remember { mutableStateOf(note.title) }
    val content = remember { mutableStateOf(note.content) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = note.color)
        ) {
            OutlinedTextField(
                value = title.value,
                onValueChange = {
                    title.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
            )
        }
        Column(modifier = Modifier.fillMaxSize().verticalScroll(state = rememberScrollState())) {
            OutlinedTextField(
                value = content.value,
                onValueChange = {
                    content.value = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = note.color),
                    modifier = Modifier
                        .padding(16.dp)
                        .shadow(elevation = 16.dp, shape = RoundedCornerShape(30.dp))
                ) {
                    Text(text = "Save", color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsScreenPrev() {
    val navController = rememberNavController()
    NoteDetailsScreen(navController, 1L)
}