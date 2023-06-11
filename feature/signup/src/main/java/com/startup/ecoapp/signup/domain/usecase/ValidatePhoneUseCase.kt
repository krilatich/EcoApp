package com.startup.ecoapp.signup.domain.usecase

import android.util.Patterns

class ValidatePhoneUseCase {
    operator fun invoke(field: String): ValidationResult {
        if(!Patterns.PHONE.matcher(field).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That's not a valid phone"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}