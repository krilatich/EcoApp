package com.startup.shared.subscriptions.domain.repository

import com.startup.shared.subscriptions.domain.entity.CreationResponse
import com.startup.shared.subscriptions.domain.entity.SubCreate
import com.startup.shared.subscriptions.domain.entity.SubscriptionBlog
import com.startup.shared.subscriptions.domain.entity.SubscriptionEvent

interface SubscriptionsRepository {

	suspend fun subscribe(subscription: SubCreate): CreationResponse

	suspend fun getBlogsSubscriptions(
		userId: String,
		filter: String,
		page: String
	): List<SubscriptionBlog>

	suspend fun getEventsSubscriptions(
		userId: String,
		filter: String,
		page: String
	): List<SubscriptionEvent>

	suspend fun delete(id: String)

	suspend fun delete(userId: String, blogId: String)
}