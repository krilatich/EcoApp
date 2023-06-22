package com.startup.shared.categories.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesDto(
	@Json(name = "categories") val categories: List<CategoryDto>,
)

@JsonClass(generateAdapter = true)
data class CategoryDto(
	@Json(name = "category_id") val categoryId: String,
	@Json(name = "category_name") val categoryName: String
)
