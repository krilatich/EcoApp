package com.startup.post.creation.domain.usecase

import com.startup.post.creation.domain.entity.PostChanges
import com.startup.post.creation.domain.repository.PostCreationRepository

class EditPostUseCase(private val repository: PostCreationRepository) {

	suspend operator fun invoke(id: String, changes: PostChanges) {
		repository.edit(id, changes)
	}
}