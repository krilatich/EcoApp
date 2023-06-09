package com.startup.ecoapp.signin.data.api

import com.startup.ecoapp.core.network.token.data.dto.AuthTokenPairDto
import com.startup.ecoapp.signin.data.dto.SignInDataDto
import retrofit2.http.POST

interface SignInApi {

	@POST("api/account/login")
	suspend fun signIn(data: SignInDataDto): AuthTokenPairDto
}