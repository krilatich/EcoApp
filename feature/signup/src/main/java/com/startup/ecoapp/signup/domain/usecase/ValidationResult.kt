package com.startup.ecoapp.signup.domain.usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
