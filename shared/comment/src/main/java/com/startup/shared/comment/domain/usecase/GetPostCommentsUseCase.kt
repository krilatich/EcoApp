package com.startup.shared.comment.domain.usecase

import com.startup.shared.comment.domain.repository.CommentRepository

class GetPostCommentsUseCase(private val repository: CommentRepository) {

	suspend operator fun invoke(id: String, filter: String = "", page: String) =
		repository.getPostComments(id, filter, page)
}