package com.startup.ecoapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.startup.ecoapp.feature.home.ui.HomeScreen
import com.startup.ecoapp.feature.post.ui.PostScreen
import com.startup.ecoapp.signin.ui.SignInScreen
import com.startup.ecoapp.signup.ui.SignUpScreen
import com.startup.feature.blog.presentation.ui.BlogScreen
import com.startup.feature.blogs.presentation.ui.BlogsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =/* Screen.HomeScreen.route*/Screen.SignInScreen.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.SignInScreen.route) {
            SignInScreen(navController = navController)
        }

        composable(
            "${Screen.PostScreen.route}/{postId}",
            arguments = listOf(
                navArgument("postId") { type = NavType.StringType }
            )) {
            val postId = it.arguments?.getString("postId")!!
            PostScreen(navController = navController, postId = postId)
        }

        composable(Screen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

        composable("${Screen.BlogScreen.route}/{blogId}",
            arguments = listOf(
                navArgument("blogId") { type = NavType.StringType }
            )) {
            val blogId = it.arguments?.getString("blogId")!!
            BlogScreen(navController = navController, blogId = blogId)
        }

        composable(Screen.BlogScreen.route) {
            BlogScreen(navController = navController, blogId = "")
        }

        composable(Screen.BlogsScreen.route) {
            BlogsScreen(navController = navController)
        }

        composable(Screen.ThreadsScreen.route) {
            com.example.threads.presentation.ui.ThreadsScreen(navController = navController)
        }

        composable("${Screen.ThreadScreen.route}/{threadId}",
            arguments = listOf(
                navArgument("threadId") { type = NavType.StringType }
            )) {
            val threadId = it.arguments?.getString("threadId")!!
            com.example.thread.presentation.ui.ThreadScreen(
                navController = navController,
                threadId = threadId
            )
        }
        composable(Screen.MapScreen.route){
            com.startup.feature.map.ui.MapScreen()
        }


    }
}