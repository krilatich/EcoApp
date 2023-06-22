package com.startup.ecoapp.feature.post.presentation

import com.startup.shared.comment.domain.entity.Comment
import com.startup.shared.post.domain.entity.Post

data class PostState
	(
	val post: Post = Post(
        id = "",
        blogId = "",
        blogTitle = "",
        authorFirstName = "",
        authorLastName = "",
        //val authorAvatar: String,
        title = "",
        text = "",
        categories = listOf(),
        photos = listOf(),
        created = "",
        edited = "",
        likes = 0,
        dislikes = 0,
        isLike = false,
        isDislike = false
    ),
	val comments: List<Comment> = emptyList(),
	val userComment: String = "",
	val isLoading: Boolean = false,
	val error: String? = null
)
