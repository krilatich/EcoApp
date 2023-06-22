package com.startup.shared.thread.domain.usecase

import com.startup.shared.thread.domain.repository.ThreadRepository

class EditThreadUseCase(private val repository: ThreadRepository) {

	suspend operator fun invoke(id: String, title: String) {
		repository.edit(id, title)
	}
}