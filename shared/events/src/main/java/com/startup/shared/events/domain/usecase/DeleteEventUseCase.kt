package com.startup.shared.events.domain.usecase

import com.startup.shared.events.domain.repository.EventsRepository

class DeleteEventUseCase(private val repository: EventsRepository) {

	suspend operator fun invoke(id: String) {
		repository.delete(id)
	}
}