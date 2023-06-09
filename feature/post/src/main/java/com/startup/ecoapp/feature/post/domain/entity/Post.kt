package com.startup.ecoapp.feature.post.domain.entity

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
