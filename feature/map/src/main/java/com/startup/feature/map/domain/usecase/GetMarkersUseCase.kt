package com.startup.feature.map.domain.usecase

import com.startup.feature.map.domain.entity.Marker
import com.startup.feature.map.domain.repository.MarkerRepository

class GetMarkersUseCase(private val repository: MarkerRepository) {

	suspend operator fun invoke(filter: String = "", page: String): List<Marker> =
		repository.get(filter, page)
}