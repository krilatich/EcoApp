package com.startup.shared.subscriptions.domain.usecase

import com.startup.shared.subscriptions.domain.repository.SubscriptionsRepository

class DeleteSubscriptionUseCase(private val repository: SubscriptionsRepository) {

	suspend operator fun invoke(id: String) {
		repository.delete(id)
	}

	suspend operator fun invoke(userId: String, blogId: String) {
		repository.delete(userId, blogId)
	}
}