package com.startup.shared.events.domain.usecase

import com.startup.shared.events.domain.entity.CreateEvent
import com.startup.shared.events.domain.repository.EventsRepository

class CreateEventUseCase(private val repository: EventsRepository) {

	suspend operator fun invoke(event: CreateEvent) {
		repository.create(event)
	}
}