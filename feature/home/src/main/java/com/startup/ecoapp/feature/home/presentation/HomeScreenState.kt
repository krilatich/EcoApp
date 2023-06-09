package com.startup.ecoapp.feature.home.presentation
import com.startup.ecoapp.feature.home.models.Post


data class HomeState
        (
    val posts:List<Post> = listOf(),
    val isLoading:Boolean = false,
    val error:String? = null
    )
