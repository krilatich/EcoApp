package com.example.threads.presentation

import androidx.compose.runtime.clearCompositionErrors
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thread.presentation.ThreadIntent
import com.example.thread.presentation.ThreadScreenState
import com.startup.ecoapp.core.network.token.domain.usecase.GetUserIdUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.shared.comment.domain.entity.CreatedComment
import com.startup.shared.comment.domain.usecase.CreateCommentUseCase
import com.startup.shared.comment.domain.usecase.GetThreadCommentsUseCase
import com.startup.shared.thread.domain.usecase.CreateThreadUseCase
import com.startup.shared.thread.domain.usecase.GetAllThreadsUseCase
import com.startup.shared.thread.domain.usecase.GetThreadUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThreadViewModel(
    private val getThreadUseCase: GetThreadUseCase,
    private val createCommentUseCase: CreateCommentUseCase,
    private val getThreadCommentsUseCase: GetThreadCommentsUseCase,
) : ViewModel() {

    var threadId: String? = null
    private val userId: String? = /*getUserIdUseCase()*/null
    private val _uiState = MutableStateFlow(
        ThreadScreenState()
    )
    val uiState: StateFlow<ThreadScreenState> = _uiState.asStateFlow()
    var page = 1

    private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
        _uiState.update {
            it.copy(
                isLoading = false,
                error = code.toString()
            )
        }
    }

    private fun loadThread() {
        val id = threadId ?: ""
        if (id.isNotEmpty())
            viewModelScope.launch(sendErrorHandler) {

                startLoading()
                if (threadId != null)
                    _uiState.update {
                        it.copy(thread = getThreadUseCase(id = id))
                    }
                page++
                endLoading()
            }
    }


    private fun loadComments() {
        val id = threadId ?: ""
        if (id.isNotEmpty())
            viewModelScope.launch(sendErrorHandler) {
                startLoading()
                if (threadId != null)
                    _uiState.update {
                        val comments = it.comments + (getThreadCommentsUseCase(
                            page = page.toString(),
                            threadId = id
                        ))
                        it.copy(comments = comments)
                    }
                page++
                endLoading()
            }
    }


    fun handle(intent: ThreadIntent) {
        when (intent) {
            is ThreadIntent.LoadThread -> loadThread()
            is ThreadIntent.CreateComment -> createComment()
            is ThreadIntent.ChangeUserComment -> changeUserComment(intent.comment)
            is ThreadIntent.LoadComments -> loadComments()
        }
    }

    private fun changeUserComment(string: String) {
        _uiState.update {
            it.copy(userComment = string)
        }
    }

    private fun createComment() {
        ////////////
        viewModelScope.launch {
            startLoading()
            /*
            createCommentUseCase(
            CreatedComment(
                userId = userId,
                threadId = threadId,
                text = uiState.value.userComment
            )
            )
             */
            _uiState.update {
                it.copy(userComment = "")
            }
            endLoading()
        }
    }


    private fun startLoading() {
        _uiState.update { it.copy(isLoading = true) }
    }

    private fun endLoading() {
        _uiState.update { it.copy(isLoading = false) }
    }
}