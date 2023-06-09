package com.startup.ecoapp.signin.data.repository

import com.startup.ecoapp.core.network.token.data.mapper.toEntity
import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair
import com.startup.ecoapp.signin.data.api.SignInApi
import com.startup.ecoapp.signin.data.mapper.toDto
import com.startup.ecoapp.signin.domain.entity.SignInData
import com.startup.ecoapp.signin.domain.repository.SignInRepository

class SignInRepositoryImpl(private val api: SignInApi) : SignInRepository {

	override suspend fun signIn(data: SignInData): AuthTokenPair = api.signIn(data.toDto()).toEntity()
}