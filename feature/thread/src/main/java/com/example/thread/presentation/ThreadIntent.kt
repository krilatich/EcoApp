package com.example.thread.presentation

sealed class ThreadIntent {
    object LoadThread : ThreadIntent()
    object LoadComments : ThreadIntent()
    object CreateComment : ThreadIntent()
    class ChangeUserComment(val comment: String) : ThreadIntent()
}
