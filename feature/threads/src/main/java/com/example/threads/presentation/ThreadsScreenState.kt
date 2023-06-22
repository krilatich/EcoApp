package com.example.threads.presentation

import com.startup.shared.thread.domain.entity.Thread

data class ThreadsScreenState(
    val threads: List<Thread> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val createDialogOpen: Boolean = false,
    val userThreadTitle: String = "",
)
