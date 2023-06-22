package com.startup.feature.blog.presentation

import com.startup.feature.blog.domain.entity.Blog
import com.startup.shared.post.domain.entity.Post


data class BlogScreenState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val posts: List<Post> = emptyList(),
    val blog: Blog,
    val isAdmin: Boolean = false
)
