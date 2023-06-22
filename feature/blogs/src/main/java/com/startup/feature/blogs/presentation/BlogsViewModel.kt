package com.startup.feature.blogs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.token.domain.usecase.GetUserIdUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.feature.blogs.domain.entity.NewBlog
import com.startup.feature.blogs.domain.usecase.CreateBlogUseCase
import com.startup.feature.blogs.domain.usecase.GetBlogsUseCase
import com.startup.feature.blogs.domain.usecase.GetUserBlogsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogsViewModel(
    private val getBlogsUseCase: GetBlogsUseCase,
    private val getUserBlogsUseCase: GetUserBlogsUseCase,
    private val createBlogUseCase: CreateBlogUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    private val userId = /*getUserIdUseCase()*/""
    private val blogId = "aeadca14-2d03-47ce-8862-2f0c04cb4197"

    private val _uiState = MutableStateFlow(
        BlogsScreenState()
    )
    val uiState: StateFlow<BlogsScreenState> = _uiState.asStateFlow()
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
        loadBlogs()
    }

    private fun loadBlogs() {
        viewModelScope.launch(sendErrorHandler) {
            startLoading()
            _uiState.update {
                val blogs = it.blogs + (getBlogsUseCase(page = page.toString()))
                it.copy(blogs = blogs)
            }
            page++
            endLoading()
        }
    }


    fun handle(intent: BlogsIntent) {
        when (intent) {
            is BlogsIntent.LoadBlogs -> loadBlogs()
            is BlogsIntent.CreateBlog -> createBlog()
            is BlogsIntent.OpenDialog -> openDialog()
            is BlogsIntent.SubscribeBlog -> subscribeBlog()
            is BlogsIntent.ChangeBlogDescription -> changeUserBlogDescription(intent.description)
            is BlogsIntent.ChangeBlogTitle -> changeUserBlogTitle(intent.title)
        }
    }

    private fun changeUserBlogTitle(string: String) {
        _uiState.update {
            it.copy(userBlogTitle = string)
        }
    }

    private fun changeUserBlogDescription(string: String) {
        _uiState.update {
            it.copy(userBlogDescription = string)
        }
    }

    private fun createBlog() {
        ////////////
        viewModelScope.launch {
            createBlogUseCase(
                newBlog = NewBlog(
                    userId = userId,
                    title = uiState.value.userBlogTitle,
                    description = uiState.value.userBlogDescription,
                    avatar = ""
                )
            )
            _uiState.update {
                it.copy(createDialogOpen = false)
            }
        }
    }

    private fun subscribeBlog() {

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