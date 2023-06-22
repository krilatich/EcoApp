package com.startup.feature.blog.domain.repository

import com.startup.feature.blog.domain.entity.Blog
import com.startup.feature.blog.domain.entity.BlogChanges

interface BlogRepository {

	suspend fun getBlog(id: String): Blog

	suspend fun editBlog(id: String, changes: BlogChanges)

	suspend fun deleteBlog(id: String)
}