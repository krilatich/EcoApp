package com.startup.shared.subscriptions.domain.usecase

import com.startup.shared.subscriptions.domain.entity.SubCreate
import com.startup.shared.subscriptions.domain.repository.SubscriptionsRepository

class SubscribeUseCase(private val repository: SubscriptionsRepository) {

	suspend operator fun invoke(userId: String, blogId: String) {
		repository.subscribe(SubCreate(userId = userId, blogId = blogId))
	}
}