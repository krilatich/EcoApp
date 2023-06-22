package com.example.threads.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.token.domain.usecase.GetUserIdUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.shared.thread.domain.usecase.CreateThreadUseCase
import com.startup.shared.thread.domain.usecase.GetAllThreadsUseCase
import com.startup.shared.thread.presentation.ThreadsIntent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThreadsViewModel(
    private val getAllThreadsUseCase: GetAllThreadsUseCase,
    private val createThreadUseCase: CreateThreadUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    private val userId = /*getUserIdUseCase()*/""

    private val _uiState = MutableStateFlow(
        ThreadsScreenState()
    )
    val uiState: StateFlow<ThreadsScreenState> = _uiState.asStateFlow()
    var page = 1

    private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
        _uiState.update {
            it.copy(
                isLoading = false,
                error = code.toString()
            )
        }
    }

    init {
        loadThreads()
    }

    private fun loadThreads() {
        viewModelScope.launch(sendErrorHandler) {
            startLoading()
            _uiState.update {
                val threads = it.threads + (getAllThreadsUseCase(page = page.toString()))
                it.copy(threads = threads)
            }
            page++
            endLoading()
        }
    }


    fun handle(intent: ThreadsIntent) {
        when (intent) {
            is ThreadsIntent.LoadThreads -> loadThreads()
            is ThreadsIntent.CreateThread -> createBlog()
            is ThreadsIntent.OpenDialog -> openDialog()
            is ThreadsIntent.ChangeThreadTitle -> changeUserThreadTitle(intent.title)
        }
    }

    private fun changeUserThreadTitle(string: String) {
        _uiState.update {
            it.copy(userThreadTitle = string)
        }
    }

    private fun createBlog() {
        ////////////
        viewModelScope.launch {

            createThreadUseCase(
                title = uiState.value.userThreadTitle,
                userId = userId
            )

            _uiState.update {
                it.copy(createDialogOpen = false)
            }
        }
    }

    private fun openDialog() {
        _uiState.update {
            it.copy(createDialogOpen = true)
        }
    }

    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }
}