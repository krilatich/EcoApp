package com.startup.ecoapp.feature.home.presentation

import androidx.lifecycle.ViewModel
import com.startup.ecoapp.feature.home.usecase.GetPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

	private val _uiState = MutableStateFlow(HomeState())
	val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

	init {
		_uiState.update {
			it.copy(posts = getPostsUseCase())
		}
	}
	fun handle(intent: HomeIntent) {
		when (intent) {
			is HomeIntent.UpdatePostsIntent -> {
				_uiState.update {
					it.copy(posts = getPostsUseCase())
				}
			}

			is HomeIntent.PutUpVote -> {
				TODO()
			}

			is HomeIntent.PutDownVote -> {
				TODO()
			}

		}
	}

}