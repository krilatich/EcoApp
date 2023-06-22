package com.startup.shared.subscriptions.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubCreateDto(
	@Json(name = "user_id") val userId: String,
	@Json(name = "blog_id") val blogId: String
)