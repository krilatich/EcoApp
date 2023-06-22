package com.startup.shared.thread.domain.usecase

import com.startup.shared.thread.domain.entity.Thread
import com.startup.shared.thread.domain.repository.ThreadRepository

class GetThreadUseCase(private val repository: ThreadRepository) {

	suspend operator fun invoke(id: String): Thread =
		repository.get(id)
}