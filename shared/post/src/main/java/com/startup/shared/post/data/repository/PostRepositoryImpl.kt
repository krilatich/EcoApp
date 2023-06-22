package com.startup.shared.post.data.repository

import com.startup.shared.post.data.api.PostApi
import com.startup.shared.post.data.mapper.toEntity
import com.startup.shared.post.domain.entity.Post
import com.startup.shared.post.domain.repository.PostRepository

class PostRepositoryImpl(private val api: PostApi) : PostRepository {

	override suspend fun getPosts(filter: String, page: String): List<Post> =
		api.getPosts(filter = filter, page = page).posts.map { it.toEntity() }

	override suspend fun getBlogPosts(blogId: String, filter: String, page: String): List<Post> =
		api.getBlogPosts(blogId, filter, page).posts.map { it.toEntity() }

	override suspend fun getPost(id: String): Post = api.getPost(id).toEntity()

}