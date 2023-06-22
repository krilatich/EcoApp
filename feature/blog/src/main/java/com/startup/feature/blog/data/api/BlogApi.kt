package com.startup.feature.blog.data.api

import com.startup.feature.blog.data.dto.BlogChangesDto
import com.startup.feature.blog.data.dto.BlogDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BlogApi {

	@GET("api/blogs/{id}")
	suspend fun getBlog(@Path("id") id: String): BlogDto

	@PUT("api/blogs/{id}")
	suspend fun editBlog(@Path("id") id: String, @Body changes: BlogChangesDto)

	@DELETE("api/blogs/{id}")
	suspend fun deleteBlog(@Path("id") id: String)
}