package com.startup.shared.categories.domain.usecase

import com.startup.shared.categories.domain.entity.Category
import com.startup.shared.categories.domain.repository.CategoriesRepository

class GetCategoriesUseCase(private val repository: CategoriesRepository) {

	suspend operator fun invoke(filter: String = "", page: String): List<Category> =
		repository.getCategories(filter, page)
}