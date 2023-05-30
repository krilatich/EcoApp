package com.example.ecoapp.model

import android.graphics.drawable.Drawable

data class Post(
    val text:String,
    val header:String,
    val types:List<String>,
    val avatarId:Int,
    val author:String,
    val time: String,
    val imageId:Int,
    val upVote:Int,
    val downVote:Int
)
