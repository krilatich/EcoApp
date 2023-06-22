package com.startup.shared.events.domain.entity

data class CreateEvent(
	val id: String,
	val userId: String,
	val eventName: String,
	val eventPlace: String,
	val eventDate: String,
	val eventTimeStart: String,
	val eventTimeEnd: String,
	val cityId: String,
	val description: String
)