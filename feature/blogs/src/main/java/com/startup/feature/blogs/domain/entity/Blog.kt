package com.startup.feature.blogs.domain.entity

import com.startup.shared.post.domain.entity.Photo

data class Blog(
	val blogId: String,
	val userId: String,
	val authorFirstName: String,
	val authorLastName: String,
	val title: String,
	val description: String,
	val avatar: List<Photo>
)
