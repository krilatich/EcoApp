package com.startup.feature.profile.domain.usecase

import com.startup.feature.profile.domain.entity.ProfileChanges
import com.startup.feature.profile.domain.repository.ProfileRepository

class ChangeProfileUseCase(private val repository: ProfileRepository) {

	suspend operator fun invoke(changes: ProfileChanges) {
		repository.changeProfile(changes)
	}
}