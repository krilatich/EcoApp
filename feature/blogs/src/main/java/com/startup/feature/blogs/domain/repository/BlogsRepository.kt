package com.startup.feature.blogs.domain.repository

import com.startup.feature.blogs.domain.entity.Blog
import com.startup.feature.blogs.domain.entity.NewBlog

interface BlogsRepository {

	suspend fun getBlogs(filter: String, page: String): List<Blog>

	suspend fun getUserBlogs(id: String, filter: String, page: String): List<Blog>

	suspend fun createBlog(newBlog: NewBlog)
}