package com.startup.shared.subscriptions.data.mapper

import com.startup.shared.subscriptions.data.dto.CreationResponseDto
import com.startup.shared.subscriptions.domain.entity.CreationResponse

internal fun CreationResponseDto.toEntity() = CreationResponse(
	status = status,
	message = message
)