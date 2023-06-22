package com.startup.feature.profile.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileChangesDto(
	@Json(name = "first_name") val firstname: String,
	@Json(name = "last_name") val lastname: String,
	@Json(name = "birth_date") val birthDate: String,
	@Json(name = "phone") val phone: String,
	@Json(name = "city") val city: String,
	@Json(name = "email") val email: String,
	@Json(name = "password") val password: String,
)