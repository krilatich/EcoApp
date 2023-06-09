package com.startup.ecoapp.signin.domain.usecase

import com.startup.ecoapp.core.network.token.domain.model.AuthTokenPair
import com.startup.ecoapp.signin.domain.entity.SignInData
import com.startup.ecoapp.signin.domain.repository.SignInRepository

class SignInUseCase(private val repository: SignInRepository) {

	suspend operator fun invoke(data: SignInData): AuthTokenPair = repository.signIn(data)
}