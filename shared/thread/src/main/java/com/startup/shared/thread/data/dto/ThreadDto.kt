package com.startup.shared.thread.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThreadResponseDto(
	@Json(name = "threads") val threads: List<ThreadDto>
)

@JsonClass(generateAdapter = true)
data class ThreadDto(
	@Json(name = "thread_id") val threadId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "user_first_name") val userFirstName: String,
	@Json(name = "user_last_name") val userLastName: String,
	@Json(name = "user_avatar") val userAvatar: List<AvatarDto>,
	@Json(name = "thread_title") val threadTitle: String,
	@Json(name = "created") val created: String,
	@Json(name = "edited") val edited: String,
)

@JsonClass(generateAdapter = true)
data class AvatarDto(
	@Json(name = "photo_place_id") val photoPlaceId: String,
	@Json(name = "photo_id") val photoId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "photo_path") val photoPath: String
)