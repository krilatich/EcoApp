package com.startup.feature.blog.domain.entity

data class BlogChanges(
	val userId: String,
	val title: String,
	val description: String,
	val avatar: String,
)