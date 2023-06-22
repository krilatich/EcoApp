package com.startup.shared.categories.domain.repository

import com.startup.shared.categories.domain.entity.Category

interface CategoriesRepository {

	suspend fun getCategories(filter: String, page: String): List<Category>

	suspend fun createCategory(category: String)
}