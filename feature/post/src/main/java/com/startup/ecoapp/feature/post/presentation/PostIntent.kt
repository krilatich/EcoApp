package com.startup.ecoapp.feature.post.presentation

sealed class PostIntent {
    object LoadPost : PostIntent()
    object LoadComments : PostIntent()
    class UpdateUserComment(val text: String) : PostIntent()
    class CreateComment(val text: String, val postId: String) : PostIntent()
    class UpVote(val postId: String) : PostIntent()
    class DownVote(val postId: String) : PostIntent()
    class CancelVote(val reactionId: String) : PostIntent()
    class CommentUpVote(val commentId: String) : PostIntent()
    class CommentDownVote(val commentId: String) : PostIntent()
    class CommentCancelVote(val reactionId: String) : PostIntent()
}