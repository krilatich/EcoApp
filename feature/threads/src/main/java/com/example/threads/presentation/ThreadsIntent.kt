package com.startup.shared.thread.presentation

sealed class ThreadsIntent {
    object LoadThreads : ThreadsIntent()
    object CreateThread : ThreadsIntent()

    object OpenDialog : ThreadsIntent()
    class ChangeThreadTitle(val title: String) : ThreadsIntent()
}
