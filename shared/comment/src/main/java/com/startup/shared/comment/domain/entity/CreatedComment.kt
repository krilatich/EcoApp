package com.startup.shared.comment.domain.entity

data class CreatedComment(
	val userId: String,
	val postId: String? = null,
	val threadId: String? = null,
	val text: String
)
