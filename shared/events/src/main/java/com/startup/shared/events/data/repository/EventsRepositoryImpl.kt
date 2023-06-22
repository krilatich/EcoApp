package com.startup.shared.events.data.repository

import com.startup.shared.events.data.api.EventsApi
import com.startup.shared.events.data.mapper.toDto
import com.startup.shared.events.data.mapper.toEntity
import com.startup.shared.events.domain.entity.CreateEvent
import com.startup.shared.events.domain.entity.Event
import com.startup.shared.events.domain.repository.EventsRepository

class EventsRepositoryImpl(private val api: EventsApi) : EventsRepository {

	override suspend fun getAll(filter: String, page: String): List<Event> =
		api.getAll(filter, page).events.map { it.toEntity() }

	override suspend fun create(event: CreateEvent) {
		api.create(event.toDto())
	}

	override suspend fun get(id: String): Event =
		api.get(id).toEntity()

	override suspend fun edit(id: String, event: CreateEvent) {
		api.edit(id, event.toDto())
	}

	override suspend fun delete(id: String) {
		api.delete(id)
	}
}