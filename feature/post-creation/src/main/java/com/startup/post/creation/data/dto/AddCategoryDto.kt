package com.startup.post.creation.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddCategoryDto(
	@Json(name = "post_id") val postId: String,
	@Json(name = "category_id") val categoryId: String,
)
