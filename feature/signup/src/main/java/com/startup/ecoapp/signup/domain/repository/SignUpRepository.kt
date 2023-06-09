package com.startup.ecoapp.signup.domain.repository

import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair
import com.startup.ecoapp.signup.domain.entity.SignUpData

interface SignUpRepository {

	suspend fun signUp(data: SignUpData): AuthTokenPair
}