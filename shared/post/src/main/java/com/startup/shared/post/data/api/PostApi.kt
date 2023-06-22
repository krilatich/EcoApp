package com.startup.shared.post.data.api

import com.startup.shared.post.data.dto.BlogPostsResponseDto
import com.startup.shared.post.data.dto.PostDto
import com.startup.shared.post.data.dto.PostResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

	@GET("api/posts")
	suspend fun getPosts(
		@Query("filter") filter: String,
		@Query("page") page: String
	): PostResponseDto

    @GET("api/blogs/{id}/posts")
    suspend fun getBlogPosts(
        @Path("id") blogId: String,
        @Query("filter") filter: String,
        @Query("page") page: String
    ): BlogPostsResponseDto

    @GET("api/posts/{id}")
	suspend fun getPost(@Path("id") id: String): PostDto

}