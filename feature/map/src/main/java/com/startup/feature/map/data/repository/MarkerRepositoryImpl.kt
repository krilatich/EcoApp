package com.startup.feature.map.data.repository

import com.startup.feature.map.data.api.MarkerApi
import com.startup.feature.map.data.mapper.toEntity
import com.startup.feature.map.domain.entity.Marker
import com.startup.feature.map.domain.repository.MarkerRepository

class MarkerRepositoryImpl(private val api: MarkerApi) : MarkerRepository {

	override suspend fun get(filter: String, page: String): List<Marker> =
		api.get(filter, page).markers.map { it.toEntity() }
}