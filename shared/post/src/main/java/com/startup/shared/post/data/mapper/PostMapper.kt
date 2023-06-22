package com.startup.shared.post.data.mapper

import com.startup.shared.post.data.dto.BlogPhotoDto
import com.startup.shared.post.data.dto.CategoryDto
import com.startup.shared.post.data.dto.PostDto
import com.startup.shared.post.data.dto.PostPhotoDto
import com.startup.shared.post.data.dto.UserPhotoDto
import com.startup.shared.post.domain.entity.Category
import com.startup.shared.post.domain.entity.Photo
import com.startup.shared.post.domain.entity.Post

fun PostDto.toEntity() = Post(
    id = postId,
    title = postTitle,
    text = postText,
    blogId = blogId,
    blogTitle = blogTitle,
    //authorAvatar = authorAvatar,
    authorFirstName = authorFirstName,
    authorLastName = authorLastName,
    categories = categories.map { it.toEntity() },
    photos = photos.map { it.toEntity() },
    created = created ?: "now",
    edited = edited,
    likes = likes,
    dislikes = dislikes,
    isDislike = isDislike,
    isLike = isLike
)

fun CategoryDto.toEntity() = Category(
    id = id,
    name = name
)

fun PostPhotoDto.toEntity() = Photo(
    photo_id = photo_id,
    photo_path = photo_path,
    photo_place_id = photo_place_id,
    id = post_id
)

fun UserPhotoDto.toEntity() = Photo(
    photo_id = photo_id,
    photo_path = photo_path,
    photo_place_id = photo_place_id,
    id = user_id
)

fun BlogPhotoDto.toEntity() = Photo(
    photo_id = photo_id,
    photo_path = photo_path,
    photo_place_id = photo_place_id,
    id = blog_id
)