package com.startup.ecoapp.feature.post.usecase

import com.startup.ecoapp.feature.post.domain.entity.Comment
import com.startup.ecoapp.feature.post.domain.entity.Post

class GetPostByIdUseCase {
    operator fun invoke() = Post(
        text = "",
        author = "",
        avatarId = 0,
        imageId = 0,
        downVote = 0,
        header = "",
        types = listOf(),
        time = "",
        upVote = 0
    )
}