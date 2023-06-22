package com.startup.shared.subscriptions.data.api

import com.startup.shared.subscriptions.data.dto.ResponseDto
import com.startup.shared.subscriptions.data.dto.SubCreateDto
import com.startup.shared.subscriptions.data.dto.SubscriptionBlogsResponseDto
import com.startup.shared.subscriptions.data.dto.SubscriptionEventsResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SubscriptionsApi {

	@POST("api/subscriptions")
	suspend fun subscribe(@Body subscription: SubCreateDto): ResponseDto

	@GET("api/subscriptions/{user_id}")
	suspend fun getBlogsSubscriptions(
		@Path("user_id") userId: String,
		@Query("filter") filter: String,
		@Query("page") page: String
	): SubscriptionBlogsResponseDto

	@GET("api/subscriptions/events/{user_id}")
	suspend fun getEventsSubscriptions(
		@Path("user_id") userId: String,
		@Query("filter") filter: String,
		@Query("page") page: String
	): SubscriptionEventsResponseDto

	@DELETE("api/subscriptions/{id}")
	suspend fun delete(@Path("id") id: String)

	@DELETE("api/subscriptions/users/{user_id}/blogs/{blog_id}")
	suspend fun delete(@Path("user_id") userId: String, @Path("blog_id") blogId: String)
}