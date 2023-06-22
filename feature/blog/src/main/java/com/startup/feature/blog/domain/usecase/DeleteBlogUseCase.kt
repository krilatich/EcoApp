package com.startup.feature.blog.domain.usecase

import com.startup.feature.blog.domain.repository.BlogRepository

class DeleteBlogUseCase(private val repository: BlogRepository) {

	suspend operator fun invoke(id: String) {
		repository.deleteBlog(id)
	}
}