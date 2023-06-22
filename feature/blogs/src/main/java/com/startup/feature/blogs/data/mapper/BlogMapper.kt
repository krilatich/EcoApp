package com.startup.feature.blogs.data.mapper

import com.startup.feature.blogs.data.dto.BlogDto
import com.startup.feature.blogs.domain.entity.Blog
import com.startup.shared.post.data.mapper.toEntity

internal fun BlogDto.toEntity() = Blog(
    blogId = blogId,
    userId = userId,
    authorFirstName = authorFirstName,
    authorLastName = authorLastName,
    title = title,
    description = description,
    avatar = avatar.map { it.toEntity() }
)