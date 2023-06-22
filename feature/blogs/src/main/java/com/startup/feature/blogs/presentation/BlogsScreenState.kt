package com.startup.feature.blogs.presentation

import com.startup.feature.blogs.domain.entity.Blog


data class BlogsScreenState(
    val blogs: List<Blog> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val createDialogOpen: Boolean = false,
    val userBlogTitle: String = "",
    val userBlogDescription: String = ""
)
