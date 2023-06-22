package com.startup.shared.categories.data.repository

import com.startup.shared.categories.data.api.CategoriesApi
import com.startup.shared.categories.data.dto.CategoryNameDto
import com.startup.shared.categories.data.mapper.toEntity
import com.startup.shared.categories.domain.entity.Category
import com.startup.shared.categories.domain.repository.CategoriesRepository

class CategoriesRepositoryImpl(private val api: CategoriesApi) : CategoriesRepository {

	override suspend fun getCategories(filter: String, page: String): List<Category> =
		api.getCategories(filter, page).categories.map { it.toEntity() }

	override suspend fun createCategory(category: String) {
		api.createCategory(CategoryNameDto(category))
	}
}