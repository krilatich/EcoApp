package com.startup.shared.subscriptions.domain.entity

data class SubscriptionEvent(
	val subEventId: String,
	val eventId: String,
	val userId: String,
	val userFirstName: String,
	val userLastName: String,
	val userAvatar: List<UserAvatar>,
	val eventName: String,
	val eventPlace: String,
	val eventDate: String,
	val eventTimeStart: String,
	val eventTimeEnd: String,
	val description: String,
	val cityName: String,
	val eventAvatar: List<EventAvatar>,
	val onEvent: String,
	val totalCount: Int
)

data class EventAvatar(
	val photoPlaceId: String,
	val eventId: String,
	val userId: String,
	val photoPath: String
)
