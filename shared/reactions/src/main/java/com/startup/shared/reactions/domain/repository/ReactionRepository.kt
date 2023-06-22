package com.startup.shared.reactions.domain.repository

import com.startup.shared.reactions.domain.entity.Reaction

interface ReactionRepository {

	suspend fun addReaction(reaction: Reaction)
	suspend fun deleteReaction(id: String)
}