package com.startup.post.creation.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostDto(
	@Json(name = "blog_id") val blogId: String,
	@Json(name = "post_title") val title: String,
	@Json(name = "post_text") val text: String
)
