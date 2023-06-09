package com.startup.ecoapp.feature.home.usecase

class GetPostsUseCase(private val repository: HomeScreenRepository) {
    operator fun invoke() = reposiry
}