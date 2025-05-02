package com.example.noteapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun MainNoteScreen(navController: NavController, viewModel: NoteViewModel, modifier: Modifier) {
    val notes = viewModel.notesList.collectAsState()

    Column( modifier = modifier
        .fillMaxSize()
        .padding(horizontal =  8.dp)) {
        Text(
            text = "Note App",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 18.dp)
        )
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
            Button(
                onClick = {
                    val id = (notes.value.size+1).toLong()
                    viewModel.addNote(Note(id = id))
                    navController.navigate(Screen.DetailsScreen.route + "/${id}")
                    Log.d("DEBUG", "id: $id")
                }
            ) {
                Text(text = "New note")
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(notes.value) { note ->
                NoteItem(note, navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavController) {
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .border(
            color = Color.Black,
            width = 1.dp,
            shape = RoundedCornerShape(10.dp)
        ),
        colors = CardDefaults.cardColors(containerColor = colorResource(note.color)),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            Row {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 5.dp)
                )
                IconButton(onClick = {
                    navController.navigate(Screen.DetailsScreen.route + "/${note.id}")
                }, modifier = Modifier.size(20.dp)) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }
            }
            Text(text = note.content)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainNoteScreenPrev() {
    val navController = rememberNavController()
    val viewModel = NoteViewModel()
    MainNoteScreen(navController, viewModel, Modifier)
}
