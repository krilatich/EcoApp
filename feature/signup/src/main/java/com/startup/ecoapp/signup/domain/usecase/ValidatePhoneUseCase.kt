package com.startup.ecoapp.signup.domain.usecase

class ValidatePhoneUseCase {
    operator fun invoke(field: String): ValidationResult {
        return ValidationResult(
            successful = true
        )
    }
}