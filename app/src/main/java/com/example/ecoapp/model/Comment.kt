package com.example.ecoapp.model

data class Comment(
    val author: Author,
    val time: String,
    val text: String,
    val likes: Int,
)
