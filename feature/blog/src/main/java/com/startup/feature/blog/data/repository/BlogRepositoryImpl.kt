package com.startup.feature.blog.data.repository

import com.startup.feature.blog.data.api.BlogApi
import com.startup.feature.blog.data.mapper.toDto
import com.startup.feature.blog.data.mapper.toEntity
import com.startup.feature.blog.domain.entity.Blog
import com.startup.feature.blog.domain.entity.BlogChanges
import com.startup.feature.blog.domain.repository.BlogRepository

class BlogRepositoryImpl(private val api: BlogApi) : BlogRepository {

	override suspend fun getBlog(id: String): Blog =
		api.getBlog(id).toEntity()

	override suspend fun editBlog(id: String, changes: BlogChanges) {
		api.editBlog(id, changes.toDto())
	}

	override suspend fun deleteBlog(id: String) {
		api.deleteBlog(id)
	}
}