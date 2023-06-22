package com.startup.shared.comment.domain.entity

import com.startup.shared.post.domain.entity.Photo

data class Comment(
    val commentId: String,
    val userId: String,
    val postId: String?,
    val threadId: String?,
    val commentText: String,
    val dislikesCount: Int,
    val likesCount: Int,
    val created: String,
    val edited: String,
    val totalCount: Double,
    val userFirstName: String,
    val userLastName: String,
    val avatar: List<Photo>
)