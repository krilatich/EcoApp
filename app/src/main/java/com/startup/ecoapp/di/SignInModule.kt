package com.startup.ecoapp.di

import com.startup.ecoapp.core.network.createRetrofitService
import com.startup.ecoapp.signin.data.api.SignInApi
import com.startup.ecoapp.signin.data.repository.SignInRepositoryImpl
import com.startup.ecoapp.signin.domain.repository.SignInRepository
import com.startup.ecoapp.signin.domain.usecase.SignInUseCase
import com.startup.ecoapp.signin.presentation.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val signInModule = module {
	viewModel {
		SignInViewModel(
			signInUseCase = get(),
			saveTokenUseCase = get()
		)
	}

	factory { createRetrofitService<SignInApi>(get(named(ORIGINAL))) }

	single<SignInRepository> { SignInRepositoryImpl(get()) }

	single { SignInUseCase(get()) }
}