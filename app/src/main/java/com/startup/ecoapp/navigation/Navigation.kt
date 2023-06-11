package com.startup.ecoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.startup.ecoapp.signin.ui.SignInScreen
import com.startup.ecoapp.signup.ui.SignUpScreen

@Composable
fun Navigation() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = Screen.SignInScreen.route) {
		composable(Screen.HomeScreen.route) {
			//HomeScreen(navController = navController)
		}

		composable(Screen.SignInScreen.route) {
			SignInScreen(navController = navController)
		}

		composable(Screen.PostScreen.route) {
			//PostScreen(navController = navController)
		}

		composable(Screen.SignUpScreen.route) {
			SignUpScreen(navController = navController)
		}

	}
}