package com.startup.shared.categories.data.api

import com.startup.shared.categories.data.dto.CategoriesDto
import com.startup.shared.categories.data.dto.CategoryNameDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CategoriesApi {

	@GET("api/categories")
	suspend fun getCategories(@Query("filter") filter: String, @Query("page") page: String): CategoriesDto

	@POST("api/categories")
	suspend fun createCategory(@Body category: CategoryNameDto)
}