package com.startup.feature.blogs.domain.usecase

import com.startup.feature.blogs.domain.entity.NewBlog
import com.startup.feature.blogs.domain.repository.BlogsRepository

class CreateBlogUseCase(private val repository: BlogsRepository) {

	suspend operator fun invoke(newBlog: NewBlog) {
		repository.createBlog(newBlog)
	}
}