package com.startup.feature.blog.domain.usecase

import com.startup.feature.blog.domain.entity.Blog
import com.startup.feature.blog.domain.repository.BlogRepository

class GetBlogUseCase(private val repository: BlogRepository) {

	suspend operator fun invoke(id: String): Blog =
		repository.getBlog(id)
}