package com.startup.shared.comment.domain.usecase

import com.startup.shared.comment.domain.entity.CreatedComment
import com.startup.shared.comment.domain.repository.CommentRepository

class CreateCommentUseCase(private val repository: CommentRepository) {

	suspend operator fun invoke(comment: CreatedComment) {
		repository.createComment(comment)
	}
}