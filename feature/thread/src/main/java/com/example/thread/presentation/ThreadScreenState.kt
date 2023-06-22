package com.example.thread.presentation

import com.startup.shared.comment.domain.entity.Comment
import com.startup.shared.thread.domain.entity.Thread


data class ThreadScreenState(
    val thread: Thread? = null,
    val comments: List<Comment> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val userComment: String = "",
)
