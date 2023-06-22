package com.startup.feature.profile.data.api

import com.startup.feature.profile.data.dto.ProfileChangesDto
import com.startup.feature.profile.data.dto.ProfileDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {

	@GET("api/account/profile")
	suspend fun getProfile(): ProfileDto

	@PUT("api/account/profile")
	suspend fun changeProfile(@Body changes: ProfileChangesDto)
}