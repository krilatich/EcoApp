package com.startup.feature.blog.presentation

sealed class BlogIntent {
    data class UpVote(val postIndex: Int) : BlogIntent()
    data class DownVote(val postIndex: Int) : BlogIntent()
    data class CancelVote(val reactionId: String) : BlogIntent()
    object LoadPosts : BlogIntent()
}
