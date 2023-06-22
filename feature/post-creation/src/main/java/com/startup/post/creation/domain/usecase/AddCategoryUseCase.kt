package com.startup.post.creation.domain.usecase

import com.startup.post.creation.domain.entity.AddCategory
import com.startup.post.creation.domain.repository.PostCreationRepository

class AddCategoryUseCase(private val repository: PostCreationRepository) {

	suspend operator fun invoke(category: AddCategory) {
		repository.addCategory(category)
	}
}