package com.startup.ecoapp.signin.presentation

sealed class SignInIntent {
    object ConfirmSignIn : SignInIntent()
    class ChangeUserEmail(val email: String) : SignInIntent()
    class ChangeUserPassword(val password: String) : SignInIntent()
    object CloseErrorDialog : SignInIntent()
}