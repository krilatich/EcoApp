package com.startup.feature.profile.data.repository

import com.startup.feature.profile.data.api.ProfileApi
import com.startup.feature.profile.data.mapper.toDto
import com.startup.feature.profile.data.mapper.toEntity
import com.startup.feature.profile.domain.entity.Profile
import com.startup.feature.profile.domain.entity.ProfileChanges
import com.startup.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val api: ProfileApi) : ProfileRepository {

	override suspend fun getProfile(): Profile =
		api.getProfile().toEntity()

	override suspend fun changeProfile(changes: ProfileChanges) {
		api.changeProfile(changes.toDto())
	}
}