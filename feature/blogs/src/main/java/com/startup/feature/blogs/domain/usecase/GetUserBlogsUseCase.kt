package com.startup.feature.blogs.domain.usecase

import com.startup.feature.blogs.domain.entity.Blog
import com.startup.feature.blogs.domain.repository.BlogsRepository

class GetUserBlogsUseCase(private val repository: BlogsRepository) {

	suspend operator fun invoke(id: String, filter: String = "", page: String): List<Blog> =
		repository.getUserBlogs(id, filter, page)
}