package com.startup.post.creation.data.repository

import com.startup.post.creation.data.api.PostCreationApi
import com.startup.post.creation.data.mapper.toDto
import com.startup.post.creation.domain.entity.AddCategory
import com.startup.post.creation.domain.entity.Post
import com.startup.post.creation.domain.entity.PostChanges
import com.startup.post.creation.domain.repository.PostCreationRepository

class PostCreationRepositoryImpl(private val api: PostCreationApi) : PostCreationRepository {

	override suspend fun create(post: Post) {
		api.create(post.toDto())
	}

	override suspend fun edit(id: String, changes: PostChanges) {
		api.edit(id, changes.toDto())
	}

	override suspend fun delete(id: String) {
		api.delete(id)
	}

	override suspend fun addCategory(category: AddCategory) {
		api.addCategory(category.postId, category.toDto())
	}
}