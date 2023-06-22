package com.startup.feature.blogs.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewBlogDto(
	@Json(name = "user_id") val userId: String,
	@Json(name = "blog_title") val title: String,
	@Json(name = "blog_description") val description: String,
	@Json(name = "blog_avatar") val avatar: String,
)