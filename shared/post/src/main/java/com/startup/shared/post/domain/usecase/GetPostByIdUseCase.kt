package com.startup.shared.post.domain.usecase

import com.startup.shared.post.domain.entity.Post
import com.startup.shared.post.domain.repository.PostRepository

class GetPostByIdUseCase(private val repository: PostRepository) {

	suspend operator fun invoke(id: String): Post = repository.getPost(id)
}