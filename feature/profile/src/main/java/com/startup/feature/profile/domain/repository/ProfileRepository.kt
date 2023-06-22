package com.startup.feature.profile.domain.repository

import com.startup.feature.profile.domain.entity.Profile
import com.startup.feature.profile.domain.entity.ProfileChanges

interface ProfileRepository {

	suspend fun getProfile(): Profile

	suspend fun changeProfile(changes: ProfileChanges)
}