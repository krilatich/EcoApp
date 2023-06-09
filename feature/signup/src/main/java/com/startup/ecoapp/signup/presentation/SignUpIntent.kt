package com.startup.ecoapp.signup.presentation

sealed class SignUpIntent {
	object ConfirmSignIn : SignUpIntent()
	class ChangeUserFirstName(val firstName: String) : SignUpIntent()
	class ChangeUserLastName(val lastName: String) : SignUpIntent()
	class ChangeUserBirthDate(val birthDate: String) : SignUpIntent()
	class ChangeUserEmail(val email: String) : SignUpIntent()
	class ChangeUserPassword(val password: String) : SignUpIntent()
	class ChangeUserPasswordConfirm(val password: String, val passwordConfirm: String) : SignUpIntent()
	class ChangeUserPhone(val phone: String) : SignUpIntent()
	class ChangeUserCity(val city: String) : SignUpIntent()
}