package com.startup.post.creation.domain.repository

import com.startup.post.creation.domain.entity.AddCategory
import com.startup.post.creation.domain.entity.Post
import com.startup.post.creation.domain.entity.PostChanges

interface PostCreationRepository {

	suspend fun create(post: Post)

	suspend fun edit(id: String, changes: PostChanges)

	suspend fun delete(id: String)

	suspend fun addCategory(category: AddCategory)
}