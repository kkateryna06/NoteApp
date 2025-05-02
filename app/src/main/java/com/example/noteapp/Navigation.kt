package com.example.noteapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(navController: NavHostController = rememberNavController(),
               viewModel: NoteViewModel = viewModel(),
               modifier: Modifier
) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainNoteScreen(navController, viewModel, modifier)
        }
        composable(Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })) { entry ->
            val id = entry.arguments!!.getLong("id")
            Log.d("DEBUG", "navigation id: $id")
            NoteDetailsScreen(navController, viewModel, id, modifier)
        }
    }
}