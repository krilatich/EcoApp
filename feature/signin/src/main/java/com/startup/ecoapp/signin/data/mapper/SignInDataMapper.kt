package com.startup.ecoapp.signin.data.mapper

import com.startup.ecoapp.signin.data.dto.SignInDataDto
import com.startup.ecoapp.signin.domain.entity.SignInData

internal fun SignInData.toDto() = SignInDataDto(
	email = email,
	password = password
)