package com.startup.shared.comment.data.repository

import com.startup.shared.comment.data.api.CommentApi
import com.startup.shared.comment.data.mapper.toDto
import com.startup.shared.comment.data.mapper.toEntity
import com.startup.shared.comment.domain.entity.Comment
import com.startup.shared.comment.domain.entity.CreatedComment
import com.startup.shared.comment.domain.repository.CommentRepository

class CommentRepositoryImpl(private val api: CommentApi) : CommentRepository {

	override suspend fun getPostComments(postId: String, filter: String, page: String): List<Comment> =
		api.getPostComments(id = postId, filter = filter, page = page).comments.map { it.toEntity() }

	override suspend fun getThreadComments(threadId: String, filter: String, page: String): List<Comment> =
		api.getThreadComments(id = threadId, filter = filter, page = page).comments.map { it.toEntity() }

	override suspend fun createComment(comment: CreatedComment) {
		api.createComment(comment.toDto())
	}
}