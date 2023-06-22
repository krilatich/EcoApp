package com.startup.feature.profile.data.mapper

import com.startup.feature.profile.data.dto.ProfileChangesDto
import com.startup.feature.profile.data.dto.ProfileDto
import com.startup.feature.profile.domain.entity.Profile
import com.startup.feature.profile.domain.entity.ProfileChanges

internal fun ProfileDto.toEntity() = Profile(
	firstname = firstname,
	lastname = lastname,
	birthDate = birthDate,
	phone = phone,
	city = city,
	email = email,
	roles = roles
)

internal fun ProfileChanges.toDto() = ProfileChangesDto(
	firstname = firstname,
	lastname = lastname,
	birthDate = birthDate,
	phone = phone,
	city = city,
	email = email,
	password = password
)