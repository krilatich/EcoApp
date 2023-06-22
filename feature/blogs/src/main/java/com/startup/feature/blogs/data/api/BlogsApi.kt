package com.startup.feature.blogs.data.api

import com.startup.feature.blogs.data.dto.BlogResponseDto
import com.startup.feature.blogs.data.dto.NewBlogDto
import com.startup.feature.blogs.data.dto.UserBlogResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogsApi {

	@GET("api/blogs")
	suspend fun getBlogs(@Query("filter") filter: String, @Query("page") page: String): BlogResponseDto

	@GET("api/blog/users/{id}")
	suspend fun getUserBlogs(@Path("id") id: String, @Path("filter") filter: String, @Path("page") page: String): UserBlogResponseDto

	@POST("api/blogs")
	suspend fun createBlog(@Body newBlog: NewBlogDto)
}