package com.startup.shared.events.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateEventDto(
	@Json(name = "user_id") val userId: String,
	@Json(name = "event_name") val eventName: String,
	@Json(name = "event_place") val eventPlace: String,
	@Json(name = "event_date") val eventDate: String,
	@Json(name = "event_time_start") val eventTimeStart: String,
	@Json(name = "event_time_end") val eventTimeEnd: String,
	@Json(name = "city_id") val cityId: String,
	@Json(name = "description") val description: String
)
