package com.startup.ecoapp.presentation

import androidx.lifecycle.ViewModel
import com.startup.ecoapp.domain.usecase.CheckFirstStartUseCase

class MainActivityViewModel(
	private val checkFirstStartUseCase: CheckFirstStartUseCase
) : ViewModel() {

}