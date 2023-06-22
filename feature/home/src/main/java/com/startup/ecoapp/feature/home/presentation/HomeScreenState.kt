package com.startup.ecoapp.feature.home.presentation

import com.startup.shared.post.domain.entity.Post

data class HomeState(
	val isLoading: Boolean = false,
	val error: String? = null,
	val posts: List<Post> = emptyList()
)
