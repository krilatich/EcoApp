package com.startup.ecoapp.feature.post.presentation

import com.startup.ecoapp.feature.post.R
import com.startup.ecoapp.feature.post.domain.entity.Comment
import com.startup.ecoapp.feature.post.domain.entity.Post

data class PostState
    (
    val comments:List<Comment> = listOf(),
    val post: Post =  Post(
        text = "Post text" ,
        avatarId = R.drawable.profile_image,
        header = "Some header",
        imageId = R.drawable.post_image,
        time = "some hr ago",
        upVote = 1000,
        downVote = 100,
        types = listOf("type1", "type2"),
        author = "AuthorNickname"
    ),
    val isLoading:Boolean = false,
    val error:String? = null
)
