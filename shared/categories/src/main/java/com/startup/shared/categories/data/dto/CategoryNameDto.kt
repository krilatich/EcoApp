package com.startup.shared.categories.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryNameDto(
	@Json(name = "category_name") val categoryName: String
)
