package com.startup.ecoapp.core.network.token.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenPairDto(
	@Json(name = "token") val accessToken: String,
	@Json(name = "refresh_token") val refreshToken: String
)