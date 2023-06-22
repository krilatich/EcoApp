package com.startup.shared.thread.data.mapper

import com.startup.shared.thread.data.dto.AvatarDto
import com.startup.shared.thread.data.dto.ThreadDto
import com.startup.shared.thread.domain.entity.Avatar

internal fun ThreadDto.toEntity() = com.startup.shared.thread.domain.entity.Thread(
	threadId = threadId,
	userId = userId,
	userFirstName = userFirstName,
	userLastName = userLastName,
	userAvatar = userAvatar.map { it.toEntity() },
	threadTitle = threadTitle,
	created = created,
	edited = edited,
)

internal fun AvatarDto.toEntity() = Avatar(
	photoPlaceId = photoPlaceId,
	photoId = photoId,
	photoPath = photoPath,
	userId = userId
)