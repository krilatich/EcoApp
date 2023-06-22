package com.startup.shared.subscriptions.domain.entity

data class SubscriptionBlog(
	val blogId: String,
	val userId: String,
	val userFirstName: String,
	val userLastName: String,
	val userAvatar: List<UserAvatar>,
	val blogTitle: String,
	val blogDescription: String,
	val blogAvatar: List<BlogAvatar>,
	val isSubscribed: String
)

data class UserAvatar(
	val photoPlaceId: String,
	val photoId: String,
	val userId: String,
	val photoPath: String
)

data class BlogAvatar(
	val photoPlaceId: String,
	val photoId: String,
	val blogId: String,
	val photoPath: String
)