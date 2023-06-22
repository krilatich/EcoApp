package com.startup.shared.thread.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewThreadDto(
	@Json(name = "user_id") val userId: String,
	@Json(name = "thread_title") val threadTitle: String
)
