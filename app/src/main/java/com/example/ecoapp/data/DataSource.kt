package com.example.ecoapp.data

import com.example.ecoapp.R
import com.example.ecoapp.model.Author
import com.example.ecoapp.model.Comment
import com.example.ecoapp.model.Post

object Datasource {
    val commentList = listOf(
        Comment(
            author = Author(
                avatarId = R.drawable.profile_image,
                nickname = "AuthorNickname"),
            text = "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text",
            time = "20 min ago",
            likes = 123
            ),
        Comment(
            author = Author(
                avatarId = R.drawable.profile_image,
                nickname = "AuthorNickname"),
            text = "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text",
            time = "20 min ago",
            likes = 123
        ),
        Comment(
            author = Author(
                avatarId = R.drawable.profile_image,
                nickname = "AuthorNickname"),
            text = "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text",
            time = "20 min ago",
            likes = 123
        ),
        Comment(
            author = Author(
                avatarId = R.drawable.profile_image,
                nickname = "AuthorNickname"),
            text = "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text" +
                    "This is example of comment text",
            time = "20 min ago",
            likes = 123
        ),
    )

    val postList = listOf(
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
        Post(text = "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "This is example of post text." +
                "",
            avatarId = R.drawable.profile_image,
            header = "Some header",
            imageId = R.drawable.post_image,
            time = "2 hr ago",
            upVote = 625,
            downVote = 100,
            types = listOf("Общее","длиннопост"),
            author = "AuthorNickname"
        ),
    )
}