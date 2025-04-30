package com.example.noteapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) {
            MainNoteScreen(navController)
        }
        composable(Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
                defaultValue = 0L
                nullable = false
            })) { entry ->
            val id = entry.arguments!!.getLong("id")
            NoteDetailsScreen(navController, id)
        }
    }
}