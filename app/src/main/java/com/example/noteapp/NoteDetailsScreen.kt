package com.example.noteapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun NoteDetailsScreen(navController: NavController, viewModel: NoteViewModel, id: Long, modifier: Modifier) {
    val notes = viewModel.notesList.collectAsState()
    val index = notes.value.indexOfFirst { it.id == id }

    if (index == -1) {
        LaunchedEffect(Unit) {
            navController.navigateUp()
        }
        return
    }
    Log.d("DEBUG", "index: $index")
    val note = notes.value[index]

    val title = remember { mutableStateOf(note.title) }
    val content = remember { mutableStateOf(note.content) }
    val color = remember { mutableStateOf(note.color) }

    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(color.value))
        ) {
            OutlinedTextField(
                value = title.value,
                onValueChange = {
                    title.value = it
                },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
            )
            Row {
                IconButton(
                    onClick = {
                        showDialog.value = true
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_color_lens),
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.onEvent(event = NoteEvent.DeleteNote(
                            id = id
                        ) )
                    }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
        if (showDialog.value) {
            ColorPickDialog(showDialog, viewModel, color)
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())) {
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
                    onClick = {
                        if (title.value.isNotBlank() && content.value.isNotBlank()) {
                            viewModel.onEvent(NoteEvent.UpdateNote(
                                id = id,
                                title = title.value,
                                content = content.value,
                                color = color.value
                            ))
                            navController.navigateUp()
                        } else {
                            Toast.makeText(
                                context, "Failed to update note", Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(color.value)),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickDialog(showDialog: MutableState<Boolean>, viewModel: NoteViewModel, color: MutableState<Int>) {
    AlertDialog(
        onDismissRequest = { showDialog.value = false }
    ) {
        Column(modifier = Modifier
            .background(color = Color.White.copy(alpha = 0.7f), shape = RoundedCornerShape(15.dp))
            .padding(12.dp)
        ) {
            Text(text = "Pick a color", fontSize = 18.sp)
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(viewModel.colorsList) { colorItem ->
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(10.dp)
                            .background(
                                color = colorResource(colorItem),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                color.value = colorItem
                                showDialog.value = false
                            }
                            .border(
                                color = Color.Black.copy(alpha = 0.5f),
                                width = if (color.value == colorItem) 3.dp else 1.dp,
                                shape = RoundedCornerShape(20.dp)
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailsScreenPrev() {
    val navController = rememberNavController()
    val viewModel = NoteViewModel()
    NoteDetailsScreen(navController, viewModel, 1L, Modifier)
}