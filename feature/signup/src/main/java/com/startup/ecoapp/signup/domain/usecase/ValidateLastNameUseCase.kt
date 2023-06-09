package com.startup.ecoapp.signup.domain.usecase

class ValidateLastNameUseCase {
    operator fun invoke(field: String): ValidationResult {
        return ValidationResult(
            successful = true
        )
    }
}