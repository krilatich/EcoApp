package com.startup.shared.subscriptions.data.mapper

import com.startup.shared.subscriptions.data.dto.BlogAvatarDto
import com.startup.shared.subscriptions.data.dto.EventAvatarDto
import com.startup.shared.subscriptions.data.dto.SubCreateDto
import com.startup.shared.subscriptions.data.dto.SubscriptionBlogDto
import com.startup.shared.subscriptions.data.dto.SubscriptionEventsDto
import com.startup.shared.subscriptions.data.dto.UserAvatarDto
import com.startup.shared.subscriptions.domain.entity.BlogAvatar
import com.startup.shared.subscriptions.domain.entity.EventAvatar
import com.startup.shared.subscriptions.domain.entity.SubCreate
import com.startup.shared.subscriptions.domain.entity.SubscriptionBlog
import com.startup.shared.subscriptions.domain.entity.SubscriptionEvent
import com.startup.shared.subscriptions.domain.entity.UserAvatar

internal fun SubCreate.toDto() = SubCreateDto(userId = userId, blogId = blogId)

internal fun SubscriptionBlogDto.toEntity(): SubscriptionBlog = SubscriptionBlog(
	blogId = blogId,
	userId = userId,
	userFirstName = userFirstName,
	userLastName = userLastName,
	userAvatar = userAvatar.map { it.toEntity() },
	blogTitle = blogTitle,
	blogDescription = blogDescription,
	blogAvatar = blogAvatar.map { it.toEntity() },
	isSubscribed = isSubscribed
)

internal fun UserAvatarDto.toEntity(): UserAvatar = UserAvatar(
	photoPlaceId = photoPlaceId,
	photoId = photoId,
	userId = userId,
	photoPath = photoPath
)

internal fun BlogAvatarDto.toEntity(): BlogAvatar = BlogAvatar(
	photoPlaceId = photoPlaceId,
	photoId = photoId,
	blogId = blogId,
	photoPath = photoPath
)

internal fun EventAvatarDto.toEntity(): EventAvatar =
	EventAvatar(
		photoPlaceId = photoPlaceId,
		eventId = eventId,
		userId = userId,
		photoPath = photoPath
	)

internal fun SubscriptionEventsDto.toEntity(): SubscriptionEvent = SubscriptionEvent(
	subEventId = subEventId,
	eventId = eventId,
	userId = userId,
	userFirstName = userFirstName,
	userLastName = userLastName,
	userAvatar = userAvatar.map { it.toEntity() },
	eventName = eventName,
	eventPlace = eventPlace,
	eventDate = eventDate,
	eventTimeStart = eventTimeStart,
	eventTimeEnd = eventTimeEnd,
	description = description,
	cityName = cityName,
	eventAvatar = eventAvatar.map { it.toEntity() },
	onEvent = onEvent,
	totalCount = totalCount
)





