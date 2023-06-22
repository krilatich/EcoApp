package com.startup.shared.subscriptions.data.repository

import com.startup.shared.subscriptions.data.api.SubscriptionsApi
import com.startup.shared.subscriptions.data.mapper.toDto
import com.startup.shared.subscriptions.data.mapper.toEntity
import com.startup.shared.subscriptions.domain.entity.CreationResponse
import com.startup.shared.subscriptions.domain.entity.SubCreate
import com.startup.shared.subscriptions.domain.entity.SubscriptionBlog
import com.startup.shared.subscriptions.domain.entity.SubscriptionEvent
import com.startup.shared.subscriptions.domain.repository.SubscriptionsRepository

class SubscriptionsRepositoryImpl(private val api: SubscriptionsApi) : SubscriptionsRepository {

	override suspend fun subscribe(subscription: SubCreate): CreationResponse =
		api.subscribe(subscription.toDto()).response.toEntity()

	override suspend fun getBlogsSubscriptions(userId: String, filter: String, page: String): List<SubscriptionBlog> =
		api.getBlogsSubscriptions(userId, filter, page).subscriptionBlogs.map { it.toEntity() }

	override suspend fun getEventsSubscriptions(userId: String, filter: String, page: String): List<SubscriptionEvent> =
		api.getEventsSubscriptions(userId, filter, page).subscriptionEvents.map { it.toEntity() }

	override suspend fun delete(id: String) {
		api.delete(id)
	}

	override suspend fun delete(userId: String, blogId: String) {
		api.delete(userId, blogId)
	}
}