package com.startup.ecoapp.signin.domain.repository

import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair
import com.startup.ecoapp.signin.domain.entity.SignInData

interface SignInRepository {

	suspend fun signIn(data: SignInData): AuthTokenPair
}