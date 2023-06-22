package com.startup.shared.reactions.data.mapper

import com.startup.shared.reactions.data.dto.ReactionDto
import com.startup.shared.reactions.domain.entity.Reaction

internal fun Reaction.toDto() = ReactionDto(
	userId = userId,
	postId = postId,
	commentId = commentId,
	reaction = reaction
)