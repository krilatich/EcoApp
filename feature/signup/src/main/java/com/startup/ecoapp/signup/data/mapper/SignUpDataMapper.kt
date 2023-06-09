package com.startup.ecoapp.signup.data.mapper

import com.startup.ecoapp.signup.data.dto.SignUpDataDto
import com.startup.ecoapp.signup.domain.entity.SignUpData

internal fun SignUpData.toDto() = SignUpDataDto(
	firstName = firstName,
	lastName = lastName,
	birthdate = birthdate,
	phone = phone,
	city = city,
	email = email,
	password = password
)