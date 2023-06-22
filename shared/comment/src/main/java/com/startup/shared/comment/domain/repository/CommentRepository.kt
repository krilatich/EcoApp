package com.startup.shared.comment.domain.repository

import com.startup.shared.comment.domain.entity.Comment
import com.startup.shared.comment.domain.entity.CreatedComment

interface CommentRepository {

	suspend fun getPostComments(postId: String, filter: String, page: String): List<Comment>
	suspend fun getThreadComments(threadId: String, filter: String, page: String): List<Comment>
	suspend fun createComment(comment: CreatedComment)
}