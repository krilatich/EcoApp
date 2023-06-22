package com.startup.shared.thread.domain.entity

data class Thread(
	val threadId: String,
	val userId: String,
	val userFirstName: String,
	val userLastName: String,
	val userAvatar: List<Avatar>,
	val threadTitle: String,
	val created: String,
	val edited: String,
)

data class Avatar(
	val photoPlaceId: String,
	val photoId: String,
	val userId: String,
	val photoPath: String
)