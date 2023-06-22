package com.startup.shared.reactions.data.api

import com.startup.shared.reactions.data.dto.ReactionDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface ReactionApi {

	@POST("api/reactions")
	suspend fun addReaction(@Body reaction: ReactionDto)

	@DELETE("api/reactions/{id}")
	suspend fun deleteReaction(@Path("id") id: String)
}