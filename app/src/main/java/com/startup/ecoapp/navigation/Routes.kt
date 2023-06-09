package com.startup.ecoapp.navigation

sealed class Screen(val route: String) {
	object HomeScreen : Screen("home_screen")
	object PostScreen : Screen("post_screen")
	object SignUpScreen : Screen("signUp_screen")
}
