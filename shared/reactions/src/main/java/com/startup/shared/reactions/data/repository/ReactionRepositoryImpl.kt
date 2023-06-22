package com.startup.shared.reactions.data.repository

import com.startup.shared.reactions.data.api.ReactionApi
import com.startup.shared.reactions.data.mapper.toDto
import com.startup.shared.reactions.domain.entity.Reaction
import com.startup.shared.reactions.domain.repository.ReactionRepository

class ReactionRepositoryImpl(private val api: ReactionApi) : ReactionRepository {

	override suspend fun addReaction(reaction: Reaction) {
		api.addReaction(reaction.toDto())
	}

	override suspend fun deleteReaction(id: String) {
		api.deleteReaction(id)
	}
}