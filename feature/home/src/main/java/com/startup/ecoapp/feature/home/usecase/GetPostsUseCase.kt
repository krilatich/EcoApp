package com.startup.ecoapp.feature.home.usecase

import com.startup.ecoapp.feature.home.models.Post

class GetPostsUseCase(/*private val repository: HomeScreenRepository*/) {
    operator fun invoke() = listOf<Post>()
}