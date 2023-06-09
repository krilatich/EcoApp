package com.startup.ecoapp.feature.post.presentation

import androidx.lifecycle.ViewModel

class PostViewModel(
	private val getCommentsUseCase: GetCommentsUseCase,
	private val getPostByIdUseCase: GetPostByIdUseCase
) : ViewModel() {

	private val _uiState = MutableStateFlow(PostState())
	val uiState: StateFlow<PostState> = _uiState.asStateFlow()

	init {
		_uiState.update {
			it.copy(
				comments = getCommentsUseCase(),
				post = getPostByIdUseCase()
			)
		}
	}
	fun handle(intent: PostIntent) {
		when (intent) {
			PostIntent.LoadPost -> {
				_uiState.update {
					it.copy(
						comments = getCommentsUseCase(),
						post = getPostByIdUseCase()
					)
				}
			}
		}
	}
}
}