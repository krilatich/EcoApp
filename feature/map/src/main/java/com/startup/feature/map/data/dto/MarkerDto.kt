package com.startup.feature.map.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkerDto(
	@Json(name = "marker_place_id") val markerPlaceId: String,
	@Json(name = "x_pos") val xPos: String,
	@Json(name = "y_pos") val yPos: String,
	@Json(name = "marker") val marker: String?,
	@Json(name = "event") val event: String?
)

@JsonClass(generateAdapter = true)
data class MarkersResponseDto(
	@Json(name = "markers") val markers: List<MarkerDto>
)