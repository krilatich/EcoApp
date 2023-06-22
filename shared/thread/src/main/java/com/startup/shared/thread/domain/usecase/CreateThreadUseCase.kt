package com.startup.shared.thread.domain.usecase

import com.startup.shared.thread.domain.repository.ThreadRepository

class CreateThreadUseCase(private val repository: ThreadRepository) {

	suspend operator fun invoke(userId: String, title: String) {
		repository.create(userId, title)
	}
}