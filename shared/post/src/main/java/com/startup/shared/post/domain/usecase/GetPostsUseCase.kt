package com.startup.shared.post.domain.usecase

import com.startup.shared.post.domain.entity.Post
import com.startup.shared.post.domain.repository.PostRepository

class GetPostsUseCase(private val repository: PostRepository) {

	suspend operator fun invoke(filter: String = "", page: String): List<Post> =
		repository.getPosts(filter, page)
}