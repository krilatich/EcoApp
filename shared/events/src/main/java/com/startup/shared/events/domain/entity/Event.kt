package com.startup.shared.events.domain.entity

data class Event(
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
	val onEvent: String
)

data class UserAvatar(
	val photoPlaceId: String,
	val photoId: String,
	val userId: String,
	val photoPath: String
)

data class EventAvatar(
	val photoPlaceId: String,
	val eventId: String,
	val userId: String,
	val photoPath: String
)
