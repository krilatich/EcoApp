package com.startup.feature.profile.domain.usecase

import com.startup.feature.profile.domain.entity.Profile
import com.startup.feature.profile.domain.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {

	suspend operator fun invoke(): Profile =
		repository.getProfile()
}