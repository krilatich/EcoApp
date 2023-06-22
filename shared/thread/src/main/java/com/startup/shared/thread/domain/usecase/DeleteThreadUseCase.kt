package com.startup.shared.thread.domain.usecase

import com.startup.shared.thread.domain.repository.ThreadRepository

class DeleteThreadUseCase(private val repository: ThreadRepository) {

	suspend operator fun invoke(id: String) {
		repository.delete(id)
	}
}