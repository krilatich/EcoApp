package com.startup.ecoapp.signup.domain.usecase

import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair
import com.startup.ecoapp.signup.domain.entity.SignUpData
import com.startup.ecoapp.signup.domain.repository.SignUpRepository

class SignUpUseCase(private val repository: SignUpRepository) {

	suspend operator fun invoke(data: SignUpData): AuthTokenPair = repository.signUp(data)
}