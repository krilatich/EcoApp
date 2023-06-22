package com.startup.shared.post.domain.entity

data class Post(
    val id: String,
    val blogId: String,
    val blogTitle: String,
    val authorFirstName: String,
    val authorLastName: String,
    //val authorAvatar: String,
    val title: String,
    val text: String,
    val categories: List<Category>,
    val photos: List<Photo>,
    val created: String,
    val edited: String?,
    val likes: Int,
    val dislikes: Int,
    var isLike: Boolean,
    var isDislike: Boolean
)

data class Category(
    val id: String,
    val name: String,
)


data class Photo(
    val photo_place_id: String,
    val photo_id: String,
    val id: String,
    val photo_path: String
)

enum class UserReaction(val type: String) {
    LIKE("like"),
    DISLIKE("dislike"),
    NONE("none")
}