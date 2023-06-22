package com.startup.feature.blogs.domain.entity

data class NewBlog(
	val userId: String,
	val title: String,
	val description: String,
	val avatar: String,
)