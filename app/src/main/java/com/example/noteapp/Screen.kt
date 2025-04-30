package com.example.noteapp

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailsScreen: Screen("details_screen")
}