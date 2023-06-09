package com.startup.ecoapp.signup.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpDataDto(
	@Json(name = "first_name") val firstName: String,
	@Json(name = "last_name") val lastName: String,
	@Json(name = "birth_date") val birthdate: String,
	@Json(name = "phone") val phone: String,
	@Json(name = "city") val city: String,
	@Json(name = "email") val email: String,
	@Json(name = "password") val password: String
)