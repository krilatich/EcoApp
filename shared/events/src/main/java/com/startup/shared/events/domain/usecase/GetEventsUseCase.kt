package com.startup.shared.events.domain.usecase

import com.startup.shared.events.domain.repository.EventsRepository

class GetEventsUseCase(private val repository: EventsRepository) {

	suspend operator fun invoke(filter: String = "", page: String) =
		repository.getAll(filter, page)
}