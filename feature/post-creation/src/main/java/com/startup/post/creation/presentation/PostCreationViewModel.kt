package com.startup.post.creation.presentation

import androidx.lifecycle.ViewModel
import com.startup.post.creation.domain.usecase.AddCategoryUseCase
import com.startup.post.creation.domain.usecase.CreatePostUseCase
import com.startup.post.creation.domain.usecase.DeletePostUseCase
import com.startup.post.creation.domain.usecase.EditPostUseCase
import com.startup.shared.categories.domain.usecase.CreateCategoryUseCase
import com.startup.shared.categories.domain.usecase.GetCategoriesUseCase

class PostCreationViewModel(
	private val createPostUseCase: CreatePostUseCase,
	private val deletePostUseCase: DeletePostUseCase,
	private val editPostUseCase: EditPostUseCase,
	private val createCategoryUseCase: CreateCategoryUseCase,
	private val getCategoriesUseCase: GetCategoriesUseCase,
	private val addCategoryUseCase: AddCategoryUseCase
) : ViewModel() {

}