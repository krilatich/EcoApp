package com.startup.ecoapp.signup.data.repository

import com.startup.ecoapp.core.network.token.data.mapper.toEntity
import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair
import com.startup.ecoapp.signup.data.api.SignUpApi
import com.startup.ecoapp.signup.data.mapper.toDto
import com.startup.ecoapp.signup.domain.entity.SignUpData
import com.startup.ecoapp.signup.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val api: SignUpApi) : SignUpRepository {

	override suspend fun signUp(data: SignUpData): AuthTokenPair = api.signUp(data.toDto()).toEntity()
}