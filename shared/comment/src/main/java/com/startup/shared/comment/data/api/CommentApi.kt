package com.startup.shared.comment.data.api

import com.startup.shared.comment.data.dto.CommentResponseDto
import com.startup.shared.comment.data.dto.CreatedCommentDto
import com.startup.shared.comment.data.dto.ThreadCommentResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentApi {

	@GET("api/posts/{id}/comments")
	suspend fun getPostComments(
		@Path("id") id: String,
		@Query("filter") filter: String,
		@Query("page") page: String
	): CommentResponseDto

	@GET("api/threads/{id}/comments")
	suspend fun getThreadComments(
		@Path("id") id: String,
		@Query("filter") filter: String,
		@Query("page") page: String
	): ThreadCommentResponseDto

	@POST("api/comments")
	suspend fun createComment(@Body comment: CreatedCommentDto)
}