package com.startup.ecoapp.di

import com.startup.ecoapp.presentation.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

	viewModel {
		MainActivityViewModel(get())
	}
}
