package com.startup.post.creation.data.api

import com.startup.post.creation.data.dto.AddCategoryDto
import com.startup.post.creation.data.dto.PostChangesDto
import com.startup.post.creation.data.dto.PostDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostCreationApi {

	@POST("api/posts")
	suspend fun create(@Body post: PostDto)

	@PUT("api/posts/{id}")
	suspend fun edit(@Path("id") id: String, changes: PostChangesDto)

	@DELETE("api/posts/{id}")
	suspend fun delete(@Path("id") id: String)

	@POST("api/posts/{id}/categories")
	suspend fun addCategory(@Path("id") id: String, @Body category: AddCategoryDto)
}