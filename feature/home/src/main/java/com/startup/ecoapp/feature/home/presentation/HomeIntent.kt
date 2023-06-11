package com.startup.ecoapp.feature.home.presentation

sealed class HomeIntent{
    object UpdatePostsIntent : HomeIntent()
    class PutUpVote(val postId: String) : HomeIntent()
    class PutDownVote(val postId: String) : HomeIntent()
}
