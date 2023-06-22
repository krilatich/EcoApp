package com.startup.post.creation.domain.usecase

import com.startup.post.creation.domain.repository.PostCreationRepository

class DeletePostUseCase(private val repository: PostCreationRepository) {

	suspend operator fun invoke(id: String) {
		repository.delete(id)
	}
}