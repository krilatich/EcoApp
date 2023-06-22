package com.startup.shared.categories.data.mapper

import com.startup.shared.categories.data.dto.CategoryDto
import com.startup.shared.categories.domain.entity.Category

internal fun CategoryDto.toEntity() = Category(categoryId = categoryId, categoryName = categoryName)