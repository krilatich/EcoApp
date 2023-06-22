package com.startup.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.feature.profile.domain.usecase.ChangeProfileUseCase
import com.startup.feature.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
	private val getProfileUseCase: GetProfileUseCase,
	private val changeProfileUseCase: ChangeProfileUseCase
) : ViewModel() {

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->

	}

	private fun getProfile() {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			getProfileUseCase()
			endLoadign()
		}
	}

	private fun changeProfile() {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			//changeProfileUseCase()
			endLoadign()
		}
	}

	private fun startLoading() {

	}

	private fun endLoadign() {

	}
}