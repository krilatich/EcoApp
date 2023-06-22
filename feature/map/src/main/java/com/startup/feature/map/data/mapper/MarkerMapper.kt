package com.startup.feature.map.data.mapper

import com.startup.feature.map.data.dto.MarkerDto
import com.startup.feature.map.domain.entity.Marker

internal fun MarkerDto.toEntity() = Marker(
	markerPlaceId = markerPlaceId,
	xPos = xPos,
	yPos = yPos,
	marker = marker?:"marker",
	event = event?:"event"
)