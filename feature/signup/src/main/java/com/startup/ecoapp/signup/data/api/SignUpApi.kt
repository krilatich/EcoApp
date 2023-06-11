package com.startup.ecoapp.signup.data.api

import com.startup.ecoapp.core.network.token.data.dto.AuthTokenPairDto
import com.startup.ecoapp.signup.data.dto.SignUpDataDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpApi {

	@POST("api/account/register")
	suspend fun signUp(@Body data: SignUpDataDto): AuthTokenPairDto
}