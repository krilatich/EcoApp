package com.startup.feature.blogs.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.startup.shared.post.data.dto.BlogPhotoDto

@JsonClass(generateAdapter = true)
data class BlogResponseDto(
	@Json(name = "blogs") val blogs: List<BlogDto>
)

@JsonClass(generateAdapter = true)
data class UserBlogResponseDto(
	@Json(name = "blogs_for_owner") val blogs: List<BlogDto>
)

@JsonClass(generateAdapter = true)
data class BlogDto(
    @Json(name = "blog_id") val blogId: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "user_first_name") val authorFirstName: String,
    @Json(name = "user_last_name") val authorLastName: String,
    @Json(name = "blog_title") val title: String,
    @Json(name = "blog_description") val description: String,
    @Json(name = "blog_avatar") val avatar: List<BlogPhotoDto>
)
