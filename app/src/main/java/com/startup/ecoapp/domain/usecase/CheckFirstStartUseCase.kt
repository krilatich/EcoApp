package com.startup.ecoapp.domain.usecase

import com.startup.ecoapp.domain.repository.FirstStartRepository

class CheckFirstStartUseCase(private val repository: FirstStartRepository) {

	operator fun invoke() = repository.isFirstStart()
}