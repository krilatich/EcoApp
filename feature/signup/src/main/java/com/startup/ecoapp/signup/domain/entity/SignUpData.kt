package com.startup.ecoapp.signup.domain.entity

data class SignUpData(
	val firstName: String,
	val lastName: String,
	val birthdate: String,
	val phone: String,
	val city: String,
	val email: String,
	val password: String
)
