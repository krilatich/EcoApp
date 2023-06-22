package com.startup.feature.blog.data.mapper

import com.startup.feature.blog.data.dto.BlogChangesDto
import com.startup.feature.blog.domain.entity.BlogChanges

internal fun BlogChanges.toDto() = BlogChangesDto(
	userId = userId,
	title = title,
	description = description,
	avatar = avatar
)