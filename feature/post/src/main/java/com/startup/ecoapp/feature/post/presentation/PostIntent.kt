package com.startup.ecoapp.feature.post.presentation

sealed class PostIntent {
	object LoadPost : PostIntent()
}