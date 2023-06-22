package com.startup.post.creation.domain.usecase

import com.startup.post.creation.domain.entity.Post
import com.startup.post.creation.domain.repository.PostCreationRepository

class CreatePostUseCase(private val repository: PostCreationRepository) {

	suspend operator fun invoke(post: Post) {
		repository.create(post)
	}
}