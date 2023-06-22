package com.startup.ecoapp.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object PostScreen : Screen("post_screen")
    object SignUpScreen : Screen("signUp_screen")
    object BlogScreen : Screen("blog_screen")

    object BlogsScreen : Screen("blogs_screen")

    object SignInScreen : Screen("signIn_screen")

    object ThreadsScreen : Screen("threads_screen")
    object ThreadScreen : Screen("thread_screen")
    object MapScreen:Screen("map_screen")
}
