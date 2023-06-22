package com.startup.post.creation.data.mapper

import com.startup.post.creation.data.dto.AddCategoryDto
import com.startup.post.creation.data.dto.PostChangesDto
import com.startup.post.creation.data.dto.PostDto
import com.startup.post.creation.domain.entity.AddCategory
import com.startup.post.creation.domain.entity.Post
import com.startup.post.creation.domain.entity.PostChanges

internal fun Post.toDto() = PostDto(
	blogId = blogId,
	title = title,
	text = text
)

internal fun PostChanges.toDto() = PostChangesDto(
	title = title,
	text = text
)

internal fun AddCategory.toDto() = AddCategoryDto(postId = postId, categoryId = categoryId)