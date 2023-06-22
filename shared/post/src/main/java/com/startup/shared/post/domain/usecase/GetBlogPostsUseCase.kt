package com.startup.shared.post.domain.usecase

import com.startup.shared.post.domain.entity.Post
import com.startup.shared.post.domain.repository.PostRepository

class GetBlogPostsUseCase(private val repository: PostRepository) {

	suspend operator fun invoke(blogId: String, filter: String = "", page: String): List<Post> =
		repository.getBlogPosts(blogId, filter, page)
}