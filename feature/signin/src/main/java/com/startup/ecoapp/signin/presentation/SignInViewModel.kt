package com.startup.ecoapp.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.token.domain.usecase.SaveTokenUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.ecoapp.signin.domain.entity.SignInData
import com.startup.ecoapp.signin.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
	private val signInUseCase: SignInUseCase,
	private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

	private val _uiState = MutableStateFlow(SignInState())
	val uiState: Flow<SignInState>
		get() = _uiState.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		_uiState.update {
			it.copy(error = code)
		}
	}

	fun handle(intent: SignInIntent) {
		when (intent) {
			SignInIntent.ConfirmSignIn         -> signIn()
			is SignInIntent.ChangeUserPassword -> updatePassword(intent.password)
			is SignInIntent.ChangeUserEmail    -> updateEmail(intent.email)
			SignInIntent.CloseErrorDialog      -> updateError()
		}
	}

	private fun signIn() {
		viewModelScope.launch(sendErrorHandler) {
			val tokenPair = signInUseCase(buildSignInData())
			saveTokenUseCase(tokenPair)
		}
	}

	private fun buildSignInData() = SignInData(
		email = _uiState.value.email,
		password = _uiState.value.password
	)

	private fun updatePassword(password: String) {
		_uiState.update {
			it.copy(password = password)
		}
	}

	private fun updateEmail(email: String) {
		_uiState.update {
			it.copy(email = email)
		}
	}

	private fun updateError() {
		_uiState.update {
			it.copy(error = null)
		}
	}
}