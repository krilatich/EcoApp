package com.startup.feature.blog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.token.domain.usecase.GetUserIdUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.feature.blog.domain.entity.Blog
import com.startup.feature.blog.domain.usecase.DeleteBlogUseCase
import com.startup.feature.blog.domain.usecase.EditBlogUseCase
import com.startup.feature.blog.domain.usecase.GetBlogUseCase
import com.startup.shared.post.domain.usecase.GetBlogPostsUseCase
import com.startup.shared.reactions.DISLIKE
import com.startup.shared.reactions.LIKE
import com.startup.shared.reactions.domain.entity.Reaction
import com.startup.shared.reactions.domain.usecase.CancelVoteUseCase
import com.startup.shared.reactions.domain.usecase.DownVoteUseCase
import com.startup.shared.reactions.domain.usecase.UpVoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogViewModel(
	private val getBlogUseCase: GetBlogUseCase,
	private val editBlogUseCase: EditBlogUseCase,
	private val deleteBlogUseCase: DeleteBlogUseCase,
	private val getUserIdUseCase: GetUserIdUseCase,
	private val cancelVoteUseCase: CancelVoteUseCase,
	private val downVoteUseCase: DownVoteUseCase,
	private val upVoteUseCase: UpVoteUseCase,
	private val getBlogPostsUseCase: GetBlogPostsUseCase,
) : ViewModel() {

	private val userId = /* getUserIdUseCase()*/""
	private val blogId = "aeadca14-2d03-47ce-8862-2f0c04cb4197"

	private val _uiState = MutableStateFlow(
		BlogScreenState(
			blog = Blog(
				"",
				"",
				"",
				"",
				"",
				"",
				listOf(),
			)
		)
	)
	val uiState: StateFlow<BlogScreenState> = _uiState.asStateFlow()
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
		loadPosts()
	}

	private fun loadPosts() {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			_uiState.update {
				val posts =
					it.posts + (getBlogPostsUseCase(page = page.toString(), blogId = blogId))
				it.copy(posts = posts)
			}
			page++

		}
	}

	fun loadBlog(blogId: String) {

		viewModelScope.launch(sendErrorHandler) {
			val blog = getBlogUseCase(blogId)
			val isAdmin = blog.userId == userId
			startLoading()
			_uiState.update {
				it.copy(
					blog = blog,
					isAdmin = isAdmin
				)
			}
			endLoading()
		}
	}

	fun handle(intent: BlogIntent) {
		when (intent) {
			is BlogIntent.UpVote     -> upVote(intent.postIndex)
			is BlogIntent.DownVote   -> downVote(intent.postIndex)
			is BlogIntent.CancelVote -> cancelVote(intent.reactionId)
			is BlogIntent.LoadPosts  -> loadPosts()
		}
	}

	private fun upVote(postIndex: Int) {
		val post = uiState.value.posts[postIndex]
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			upVoteUseCase(Reaction(userId = userId, postId = post.id, reaction = LIKE))
			endLoading()
		}
		val posts = uiState.value.posts.toMutableList()
		posts[postIndex].isLike = true
		_uiState.update {
			it.copy(
				posts = posts
			)
		}
	}

	private fun downVote(postIndex: Int) {
		val post = uiState.value.posts[postIndex]
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			downVoteUseCase(Reaction(userId = userId, postId = post.id, reaction = DISLIKE))
			endLoading()
		}
		val posts = uiState.value.posts.toMutableList()
		posts[postIndex].isDislike = true
		_uiState.update {
			it.copy(
				posts = posts
			)
		}
	}

	private fun cancelVote(postId: String) {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			cancelVoteUseCase(postId)
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