package com.startup.ecoapp.signup.presentation

data class SignUpState(
	val firstName: String = "",
	val firstNameError: String? = null,
	val lastName: String = "",
	val lastNameError: String? = null,
	val birthDate: String = "",
	val birthDateError: String? = null,
	val city: String = "",
	val cityError: String? = null,
	val phone: String = "",
	val phoneError: String? = null,
	val email: String = "",
	val emailError: String? = null,
	val password: String = "",
	val passwordError: String? = null,
	val confirmPassword: String = "",
	val confirmPasswordError: String? = null,
	val isLoading: Boolean = false,
	val error: String? = null
)

