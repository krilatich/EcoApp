package com.startup.shared.thread.domain.usecase

import com.startup.shared.thread.domain.entity.Thread
import com.startup.shared.thread.domain.repository.ThreadRepository

class GetAllThreadsUseCase(private val repository: ThreadRepository) {

	suspend operator fun invoke(filter: String = "", page: String): List<Thread> =
		repository.getAll(filter, page)
}