package com.startup.shared.comment.domain.usecase

import com.startup.shared.comment.domain.entity.Comment
import com.startup.shared.comment.domain.repository.CommentRepository

class GetThreadCommentsUseCase(private val repository: CommentRepository) {

	suspend operator fun invoke(threadId: String, filter: String = "", page: String): List<Comment> =
		repository.getThreadComments(threadId, filter, page)
}