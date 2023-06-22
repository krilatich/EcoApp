package com.startup.shared.thread.domain.repository

import com.startup.shared.thread.domain.entity.Thread

interface ThreadRepository {

	suspend fun getAll(filter: String, page: String): List<Thread>

	suspend fun get(id: String): Thread

	suspend fun create(userId: String, title: String)

	suspend fun edit(id: String, title: String)

	suspend fun delete(id: String)
}