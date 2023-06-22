package com.startup.feature.blogs.domain.usecase

import com.startup.feature.blogs.domain.entity.Blog
import com.startup.feature.blogs.domain.repository.BlogsRepository

class GetBlogsUseCase(private val repository: BlogsRepository) {

	suspend operator fun invoke(filter: String = "", page: String): List<Blog> =
		repository.getBlogs(filter, page)
}