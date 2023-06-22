package com.startup.ecoapp.feature.home.presentation

sealed class HomeIntent {
    data class UpVote(val postId: String) : HomeIntent()
    data class DownVote(val postId: String) : HomeIntent()
    data class CancelVote(val reactionId: String) : HomeIntent()
    object LoadPosts : HomeIntent()
}
