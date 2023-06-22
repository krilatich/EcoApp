package com.startup.feature.blog.data.mapper

import com.startup.feature.blog.data.dto.BlogDto
import com.startup.feature.blog.domain.entity.Blog
import com.startup.shared.post.data.mapper.toEntity

internal fun BlogDto.toEntity() = Blog(
    blogId = blogId,
    userId = userId,
    authorFirstName = authorFirstName,
    authorLastName = authorLastName,
    title = title,
    description = description,
    avatar = blog_avatar.map { it.toEntity() }
)

