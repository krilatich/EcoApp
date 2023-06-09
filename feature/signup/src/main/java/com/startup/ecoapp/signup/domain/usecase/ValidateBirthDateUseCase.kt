package com.startup.ecoapp.signup.domain.usecase

class ValidateBirthDateUseCase {
    operator fun invoke(field: String): ValidationResult {
        return ValidationResult(
            successful = true
        )
    }
}