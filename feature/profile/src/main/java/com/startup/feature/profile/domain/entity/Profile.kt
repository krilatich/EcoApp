package com.startup.feature.profile.domain.entity

data class Profile(
	val firstname: String,
	val lastname: String,
	val birthDate: String,
	val phone: String,
	val city: String,
	val email: String,
	val roles: List<String>,
)