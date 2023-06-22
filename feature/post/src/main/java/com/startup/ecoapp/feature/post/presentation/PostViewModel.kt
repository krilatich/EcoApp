package com.startup.ecoapp.feature.post.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.ecoapp.core.network.token.domain.usecase.GetUserIdUseCase
import com.startup.ecoapp.core.network.utils.CoroutineNetworkExceptionHandler
import com.startup.shared.comment.domain.entity.CreatedComment
import com.startup.shared.comment.domain.usecase.CreateCommentUseCase
import com.startup.shared.comment.domain.usecase.GetPostCommentsUseCase
import com.startup.shared.post.domain.usecase.GetPostByIdUseCase
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

class PostViewModel(
	private val getCommentsUseCase: GetPostCommentsUseCase,
	private val getPostByIdUseCase: GetPostByIdUseCase,
	private val createCommentUseCase: CreateCommentUseCase,
	private val getUserIdUseCase: GetUserIdUseCase,
	private val upVoteUseCase: UpVoteUseCase,
	private val downVoteUseCase: DownVoteUseCase,
	private val cancelVoteUseCase: CancelVoteUseCase
) : ViewModel() {

	private val _uiState = MutableStateFlow(PostState())
	val uiState: StateFlow<PostState> = _uiState.asStateFlow()
    private val userId = /*getUserIdUseCase()*/""

    var postId = "8ad0dc60-6ddc-4843-912a-198e9e915872"

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
		loadPost()
		loadComments()
	}

	fun handle(intent: PostIntent) {
		when (intent) {
			is PostIntent.UpdateUserComment -> updateUserComment(intent.text)
			is PostIntent.UpVote            -> upVotePost(intent.postId)
			is PostIntent.DownVote          -> downVotePost(intent.postId)
			is PostIntent.CancelVote        -> cancelVote(intent.reactionId)
			is PostIntent.CommentUpVote     -> upVoteComment(intent.commentId)
			is PostIntent.CommentDownVote   -> downVoteComment(intent.commentId)
			is PostIntent.CommentCancelVote -> cancelVote(intent.reactionId)
			is PostIntent.CreateComment     -> createComment(
				commentText = intent.text,
				postId = intent.postId
			)

			is PostIntent.LoadPost -> loadPost()
            is PostIntent.LoadComments -> loadComments()
		}
	}

	private fun loadComments() {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			_uiState.update {
                val comments =
                    it.comments + (getCommentsUseCase(id = postId, page = page.toString()))
                it.copy(comments = comments)
            }
			page++
			endLoading()
		}
	}

	private fun updateUserComment(text: String) {
		_uiState.update {
			it.copy(userComment = text)
		}
	}

	private fun loadPost() {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			_uiState.update {
				it.copy(post = getPostByIdUseCase(postId))
			}
			endLoading()
		}
	}

	private fun createComment(commentText: String, postId: String) {
		_uiState.update {
			it.copy(userComment = "")
		}
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			createCommentUseCase(CreatedComment(userId = userId, postId = postId, text = commentText))
			endLoading()
		}
	}

	private fun upVotePost(postId: String) {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			upVoteUseCase(Reaction(userId = userId, postId = postId, reaction = LIKE))
			endLoading()
		}
	}

	private fun upVoteComment(commentId: String) {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			upVoteUseCase(Reaction(userId = userId, commentId = commentId, reaction = LIKE))
			endLoading()
		}
	}

	private fun downVotePost(postId: String) {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			downVoteUseCase(Reaction(userId = userId, postId = postId, reaction = DISLIKE))
			endLoading()
		}
	}

	private fun downVoteComment(commentId: String) {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			downVoteUseCase(Reaction(userId = userId, commentId = commentId, reaction = DISLIKE))
			endLoading()
		}
	}

	private fun cancelVote(reactionId: String) {
		viewModelScope.launch(sendErrorHandler) {
			startLoading()
			cancelVoteUseCase(reactionId)
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
