package com.startup.ecoapp.signup.domain.usecase

class ValidateFirstNameUseCase {
    operator fun invoke(field: String): ValidationResult {
        return ValidationResult(
            successful = true
        )
    }
}