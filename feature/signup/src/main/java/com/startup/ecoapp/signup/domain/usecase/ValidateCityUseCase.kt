package com.startup.ecoapp.signup.domain.usecase

class ValidateCityUseCase {

    operator fun invoke(field: String): ValidationResult {
        return ValidationResult(
            successful = true
        )
    }
}