package com.startup.shared.events.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAvatarDto(
	@Json(name = "photo_place_id") val photoPlaceId: String,
	@Json(name = "photo_id") val photoId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "photo_path") val photoPath: String
)

@JsonClass(generateAdapter = true)
data class EventAvatarDto(
	@Json(name = "photo_place_id") val photoPlaceId: String,
	@Json(name = "event_id") val eventId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "photo_path") val photoPath: String
)

@JsonClass(generateAdapter = true)
data class EventDto(
	@Json(name = "event_id") val eventId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "user_first_name") val userFirstName: String,
	@Json(name = "user_last_name") val userLastName: String,
	@Json(name = "user_avatar") val userAvatar: List<UserAvatarDto>,
	@Json(name = "event_name") val eventName: String,
	@Json(name = "event_place") val eventPlace: String,
	@Json(name = "event_date") val eventDate: String,
	@Json(name = "event_time_start") val eventTimeStart: String,
	@Json(name = "event_time_end") val eventTimeEnd: String,
	@Json(name = "description") val description: String,
	@Json(name = "city_name") val cityName: String,
	@Json(name = "event_avatar") val eventAvatar: List<EventAvatarDto>,
	@Json(name = "on_event") val onEvent: String
)

@JsonClass(generateAdapter = true)
data class EventsResponseDto(
	@Json(name = "events") val events: List<EventDto>
)
