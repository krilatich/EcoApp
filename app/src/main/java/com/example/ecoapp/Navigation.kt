package com.example.ecoapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoapp.data.Screen
import com.example.ecoapp.data.Screen.HomeScreen
import com.example.ecoapp.screens.HomeScreen
import com.example.ecoapp.screens.PostScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.PostScreen.route) {
            PostScreen(navController = navController)
        }

    }
}