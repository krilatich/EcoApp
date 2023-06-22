package com.startup.shared.comment.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.startup.shared.post.data.dto.UserPhotoDto

@JsonClass(generateAdapter = true)
data class CommentResponseDto(
    @Json(name = "comments_ratings") val comments: List<CommentDto>
)

@JsonClass(generateAdapter = true)
data class ThreadCommentResponseDto(
    @Json(name = "comments_in_thread") val comments: List<CommentDto>
)

@JsonClass(generateAdapter = true)
data class CommentDto(
    @Json(name = "comment_id") val commentId: String,
    @Json(name = "user_id") val userId: String,
    @Json(name = "user_first_name") val userFirstName: String,
    @Json(name = "user_last_name") val userLastName: String,
    @Json(name = "post_id") val postId: String?,
    @Json(name = "thread_id") val threadId: String?,
    @Json(name = "comment_text") val commentText: String,
    @Json(name = "created") val created: String,
    @Json(name = "edited") val edited: String,
    @Json(name = "count_likes") val likesCount: Int?,
    @Json(name = "count_dislikes") val dislikesCount: Int?,
    @Json(name = "total_count") val totalCount: Double?,
    @Json(name = "is_like") val isLike: Boolean,
    @Json(name = "is_dislike") val isDislike: Boolean,
    @Json(name = "user_avatar") val userAvatar: List<UserPhotoDto>?
)
