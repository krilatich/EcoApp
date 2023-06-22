package com.startup.shared.events.data.mapper

import com.startup.shared.events.data.dto.EventAvatarDto
import com.startup.shared.events.data.dto.EventDto
import com.startup.shared.events.data.dto.UserAvatarDto
import com.startup.shared.events.domain.entity.Event
import com.startup.shared.events.domain.entity.EventAvatar
import com.startup.shared.events.domain.entity.UserAvatar

internal fun EventDto.toEntity(): Event = Event(
	eventId = eventId,
	userId = userId,
	userFirstName = userFirstName,
	userLastName = userLastName,
	userAvatar = userAvatar.map { it.toEntity() },
	eventName = eventName,
	eventPlace = eventPlace,
	eventDate = eventDate,
	eventTimeStart = eventTimeStart,
	eventTimeEnd = eventTimeEnd,
	description = description,
	cityName = cityName,
	eventAvatar = eventAvatar.map { it.toEntity() },
	onEvent = onEvent
)

internal fun UserAvatarDto.toEntity(): UserAvatar = UserAvatar(
	photoPlaceId = photoPlaceId,
	photoId = photoId,
	userId = userId,
	photoPath = photoPath
)

internal fun EventAvatarDto.toEntity(): EventAvatar = EventAvatar(
	photoPlaceId = photoPlaceId,
	eventId = eventId,
	userId = userId,
	photoPath = photoPath
)
