package com.startup.shared.comment.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreatedCommentDto(
	@Json(name = "user_id") val userId: String,
	@Json(name = "post_id") val postId: String?,
	@Json(name = "thread_id") val threadId: String?,
	@Json(name = "comment_text") val text: String,
)
