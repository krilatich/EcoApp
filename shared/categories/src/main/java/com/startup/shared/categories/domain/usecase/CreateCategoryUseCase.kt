package com.startup.shared.categories.domain.usecase

import com.startup.shared.categories.domain.repository.CategoriesRepository

class CreateCategoryUseCase(private val repository: CategoriesRepository) {

	suspend operator fun invoke(category: String) {
		repository.createCategory(category)
	}
}