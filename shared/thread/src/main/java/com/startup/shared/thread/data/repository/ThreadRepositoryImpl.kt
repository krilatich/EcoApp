package com.startup.shared.thread.data.repository

import com.startup.shared.thread.data.api.ThreadApi
import com.startup.shared.thread.data.dto.EditThreadDto
import com.startup.shared.thread.data.dto.NewThreadDto
import com.startup.shared.thread.data.mapper.toEntity
import com.startup.shared.thread.domain.entity.Thread
import com.startup.shared.thread.domain.repository.ThreadRepository

class ThreadRepositoryImpl(private val api: ThreadApi) : ThreadRepository {

	override suspend fun getAll(filter: String, page: String): List<Thread> =
		api.getAll(filter, page).threads.map { it.toEntity() }

	override suspend fun get(id: String): Thread =
		api.get(id).toEntity()

	override suspend fun create(userId: String, title: String) {
		api.create(NewThreadDto(userId, title))
	}

	override suspend fun edit(id: String, title: String) {
		api.edit(id, EditThreadDto(title))
	}

	override suspend fun delete(id: String) {
		api.delete(id)
	}
}