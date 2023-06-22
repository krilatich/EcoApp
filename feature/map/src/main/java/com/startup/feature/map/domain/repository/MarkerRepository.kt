package com.startup.feature.map.domain.repository

import com.startup.feature.map.domain.entity.Marker

interface MarkerRepository {

	suspend fun get(filter: String, page: String): List<Marker>
}