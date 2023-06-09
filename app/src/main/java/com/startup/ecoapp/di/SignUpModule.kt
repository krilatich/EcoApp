package com.startup.ecoapp.di

import com.startup.ecoapp.core.network.createRetrofitService
import com.startup.ecoapp.signup.data.api.SignUpApi
import com.startup.ecoapp.signup.data.repository.SignUpRepositoryImpl
import com.startup.ecoapp.signup.domain.repository.SignUpRepository
import com.startup.ecoapp.signup.domain.usecase.SignUpUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidateBirthDateUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidateCityUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidateConfirmPasswordUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidateEmailUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidateFirstNameUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidateLastNameUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidatePasswordUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidatePhoneUseCase
import com.startup.ecoapp.signup.domain.usecase.ValidationResult
import com.startup.ecoapp.signup.presentation.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signUpModule = module {
	viewModel {
		SignUpViewModel(
			signUpUseCase = get(),
			saveTokenUseCase = get(),
			validateBirthDateUseCase = get(),
			validateCityUseCase = get(),
			validateConfirmPasswordUseCase = get(),
			validateEmailUseCase = get(),
			validateFirstNameUseCase = get(),
			validateLastNameUseCase = get(),
			validatePasswordUseCase = get(),
			validatePhoneUseCase = get()
		)
	}

	factory { createRetrofitService<SignUpApi>(get(named(ORIGINAL))) }

	single<SignUpRepository> { SignUpRepositoryImpl(get()) }

	single { SignUpUseCase(get()) }
	single { ValidateFirstNameUseCase() }
	single { ValidateLastNameUseCase() }
	single { ValidateEmailUseCase() }
	single { ValidatePasswordUseCase() }
	single { ValidateConfirmPasswordUseCase() }
	single { ValidatePhoneUseCase() }
	single { ValidateCityUseCase() }
	single { ValidateBirthDateUseCase() }

}