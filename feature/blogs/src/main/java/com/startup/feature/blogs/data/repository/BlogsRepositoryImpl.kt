package com.startup.feature.blogs.data.repository

import com.startup.feature.blogs.data.api.BlogsApi
import com.startup.feature.blogs.data.mapper.toDto
import com.startup.feature.blogs.data.mapper.toEntity
import com.startup.feature.blogs.domain.entity.Blog
import com.startup.feature.blogs.domain.entity.NewBlog
import com.startup.feature.blogs.domain.repository.BlogsRepository

class BlogsRepositoryImpl(private val api: BlogsApi) : BlogsRepository {

	override suspend fun getBlogs(filter: String, page: String): List<Blog> =
		api.getBlogs(filter, page).blogs.map { it.toEntity() }

	override suspend fun getUserBlogs(id: String, filter: String, page: String): List<Blog> =
		api.getUserBlogs(id, filter, page).blogs.map { it.toEntity() }

	override suspend fun createBlog(newBlog: NewBlog) {
		api.createBlog(newBlog.toDto())
	}
}