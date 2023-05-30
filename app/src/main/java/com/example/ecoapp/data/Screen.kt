package com.example.ecoapp.data

sealed class Screen(val route:String){
    object HomeScreen:Screen("home_screen")
    object PostScreen:Screen("post_screen")
}