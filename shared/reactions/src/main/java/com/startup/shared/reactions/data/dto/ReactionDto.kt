package com.startup.shared.reactions.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReactionDto(
	@Json(name = "user_id") val userId: String,
	@Json(name = "post_id") val postId: String?,
	@Json(name = "comment_id") val commentId: String?,
	@Json(name = "reaction") val reaction: String,
)