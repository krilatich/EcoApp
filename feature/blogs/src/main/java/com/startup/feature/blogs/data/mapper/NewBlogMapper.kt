package com.startup.feature.blogs.data.mapper

import com.startup.feature.blogs.data.dto.NewBlogDto
import com.startup.feature.blogs.domain.entity.NewBlog

internal fun NewBlog.toDto() = NewBlogDto(
	userId = userId,
	title = title,
	description = description,
	avatar = avatar
)