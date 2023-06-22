package com.startup.shared.reactions.domain.usecase

import com.startup.shared.reactions.domain.entity.Reaction
import com.startup.shared.reactions.domain.repository.ReactionRepository

class UpVoteUseCase(private val repository: ReactionRepository) {

	suspend operator fun invoke(reaction: Reaction) {
		repository.addReaction(reaction)
	}
}