package com.startup.feature.blog.domain.usecase

import com.startup.feature.blog.domain.entity.BlogChanges
import com.startup.feature.blog.domain.repository.BlogRepository

class EditBlogUseCase(private val repository: BlogRepository) {

	suspend operator fun invoke(id: String, changes: BlogChanges) {
		repository.editBlog(id, changes)
	}
}