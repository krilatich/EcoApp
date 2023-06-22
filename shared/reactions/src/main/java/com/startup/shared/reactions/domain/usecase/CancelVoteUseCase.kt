package com.startup.shared.reactions.domain.usecase

import com.startup.shared.reactions.domain.repository.ReactionRepository

class CancelVoteUseCase(private val repository: ReactionRepository) {

	suspend operator fun invoke(id: String) {
		repository.deleteReaction(id)
	}
}