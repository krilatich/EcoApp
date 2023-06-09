package com.startup.ecoapp.feature.post.domain.entity

data class Comment(
	val author: Author,
	val time: String,
	val text: String,
	val likes: Int,
)
