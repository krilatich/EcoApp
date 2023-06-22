package com.startup.feature.map.presentation

import com.startup.feature.map.domain.entity.Marker

sealed class MapState {
	data class Content(
		val markers: List<Marker>
	) : MapState()
}
