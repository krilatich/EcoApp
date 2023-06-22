package com.startup.shared.subscriptions.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDto(
	@Json(name = "response") val response: CreationResponseDto
)

@JsonClass(generateAdapter = true)
data class CreationResponseDto(
	@Json(name = "status") val status: String,
	@Json(name = "message") val message: String
)
