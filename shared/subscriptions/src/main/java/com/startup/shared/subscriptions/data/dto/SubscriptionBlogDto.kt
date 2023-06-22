package com.startup.shared.subscriptions.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubscriptionBlogsResponseDto(
	@Json(name = "subscriptions_blogs") val subscriptionBlogs: List<SubscriptionBlogDto>
)

data class SubscriptionBlogDto(
	@Json(name = "blog_id") val blogId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "user_first_name") val userFirstName: String,
	@Json(name = "user_last_name") val userLastName: String,
	@Json(name = "user_avatar") val userAvatar: List<UserAvatarDto>,
	@Json(name = "blog_title") val blogTitle: String,
	@Json(name = "blog_description") val blogDescription: String,
	@Json(name = "blog_avatar") val blogAvatar: List<BlogAvatarDto>,
	@Json(name = "is_subscribed") val isSubscribed: String
)

data class UserAvatarDto(
	@Json(name = "photo_place_id") val photoPlaceId: String,
	@Json(name = "photo_id") val photoId: String,
	@Json(name = "user_id") val userId: String,
	@Json(name = "photo_path") val photoPath: String
)

data class BlogAvatarDto(
	@Json(name = "photo_place_id") val photoPlaceId: String,
	@Json(name = "photo_id") val photoId: String,
	@Json(name = "blog_id") val blogId: String,
	@Json(name = "photo_path") val photoPath: String
)