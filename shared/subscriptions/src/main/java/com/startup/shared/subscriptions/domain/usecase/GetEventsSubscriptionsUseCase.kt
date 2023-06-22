package com.startup.shared.subscriptions.domain.usecase

import com.startup.shared.subscriptions.domain.repository.SubscriptionsRepository

class GetEventsSubscriptionsUseCase(private val repository: SubscriptionsRepository) {

	suspend operator fun invoke(userId: String, filter: String = "", page: String) =
		repository.getEventsSubscriptions(userId, filter, page)
}