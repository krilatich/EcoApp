package com.startup.shared.comment.data.mapper

import com.startup.shared.comment.data.dto.CommentDto
import com.startup.shared.comment.data.dto.CreatedCommentDto
import com.startup.shared.comment.domain.entity.Comment
import com.startup.shared.comment.domain.entity.CreatedComment
import com.startup.shared.post.data.mapper.toEntity

internal fun CreatedComment.toDto() = CreatedCommentDto(
    userId = userId,
    postId = postId,
    threadId = threadId,
    text = text
)

internal fun CommentDto.toEntity() = Comment(
    commentId = commentId,
    userId = userId,
    postId = postId,
    threadId = threadId,
    commentText = commentText,
    dislikesCount = dislikesCount ?: 0,
    likesCount = likesCount ?: 0,
    created = created,
    edited = edited,
    totalCount = totalCount ?: 0.0,
    userFirstName = userFirstName,
    userLastName = userLastName,
    avatar = userAvatar?.map { it.toEntity() } ?: listOf()
)