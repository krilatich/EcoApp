package com.startup.ecoapp.feature.home.models

import com.startup.ecoapp.feature.home.presentation.UserReaction


data class Post(
    val id: String,
    val text: String,
    val title: String,
    val categories: List<String>,
    val avatarImage: String,
    val author: String,
    val time: String,
    val image: String?,
    val upVote: Int,
    val downVote: Int,
    val userReaction: UserReaction = UserReaction.NONE
)
