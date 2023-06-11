package com.startup.ecoapp.signin.presentation

data class SignInState(
	val email: String = "",
	val password: String = "",
	val isLoading: Boolean = false,
	val error: Int? = null
)

