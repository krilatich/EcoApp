package com.startup.shared.events.domain.usecase

import com.startup.shared.events.domain.entity.CreateEvent
import com.startup.shared.events.domain.repository.EventsRepository

class EditEventUseCase(private val repository: EventsRepository) {

	suspend operator fun invoke(id: String, event: CreateEvent) {
		repository.edit(id, event)
	}
}