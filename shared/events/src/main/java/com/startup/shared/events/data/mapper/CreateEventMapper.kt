package com.startup.shared.events.data.mapper

import com.startup.shared.events.data.dto.CreateEventDto
import com.startup.shared.events.domain.entity.CreateEvent

internal fun CreateEvent.toDto() = CreateEventDto(
	userId = userId,
	eventName = eventName,
	eventPlace = eventPlace,
	eventDate = eventDate,
	eventTimeStart = eventTimeStart,
	eventTimeEnd = eventTimeEnd,
	cityId = cityId,
	description = description
)