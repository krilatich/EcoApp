package com.startup.ecoapp.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.token.domain.usecase.SaveTokenUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.ecoapp.signin.domain.entity.SignInData
import com.startup.ecoapp.signin.domain.usecase.SignInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(
	private val signInUseCase: SignInUseCase,
	private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code -> }

	private fun signIn(email: String, password: String) {
		viewModelScope.launch(sendErrorHandler) {
			val tokenPair = signInUseCase(SignInData(email, password))
			saveTokenUseCase(tokenPair)
		}
	}
}