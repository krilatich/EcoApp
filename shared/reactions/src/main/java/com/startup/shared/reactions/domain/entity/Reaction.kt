package com.startup.shared.reactions.domain.entity

data class Reaction(
	val userId: String,
	val postId: String? = null,
	val commentId: String? = null,
	val reaction: String,
)