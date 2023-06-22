package com.startup.feature.blogs.presentation

sealed class BlogsIntent {
    object LoadBlogs : BlogsIntent()
    object CreateBlog : BlogsIntent()
    object SubscribeBlog : BlogsIntent()

    object OpenDialog : BlogsIntent()
    class ChangeBlogTitle(val title: String) : BlogsIntent()
    class ChangeBlogDescription(val description: String) : BlogsIntent()
}
