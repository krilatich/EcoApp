package com.startup.shared.events.domain.repository

import com.startup.shared.events.domain.entity.CreateEvent
import com.startup.shared.events.domain.entity.Event

interface EventsRepository {

	suspend fun getAll(filter: String, page: String): List<Event>

	suspend fun create(event: CreateEvent)

	suspend fun get(id: String): Event

	suspend fun edit(id: String, event: CreateEvent)

	suspend fun delete(id: String)
}