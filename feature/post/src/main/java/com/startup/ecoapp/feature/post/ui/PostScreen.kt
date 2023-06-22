package com.startup.ecoapp.feature.post.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.startup.ecoapp.feature.post.presentation.PostIntent
import com.startup.ecoapp.feature.post.presentation.PostViewModel
import com.startup.shared.comment.domain.entity.Comment
import org.koin.androidx.compose.koinViewModel
import com.startup.theme.R as ThemeR

@Composable
fun PostScreen(
    postViewModel: PostViewModel = koinViewModel(),
    navController: NavController,
    postId: String
) {

    val state by postViewModel.uiState.collectAsState()
    val post = state.post
    val lazyColumnListState = rememberLazyListState()
    Log.d("postik", post.toString())
    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value)
            postViewModel.handle(PostIntent.LoadComments)
    }

    Scaffold(
        bottomBar = {
            PostCommentBottomBar(
                userComment = state.userComment,
                onChangeUserComment = { postViewModel.handle(PostIntent.UpdateUserComment(it)) },
                onSendCommentClick = {
                    postViewModel.handle(
                        PostIntent.CreateComment(
                            postId = post.id,
                            text = state.userComment
                        )
                    )
                }
            )
        },
        topBar = { PostNavigationTopBar(navController) })
    {
        LazyColumn(
            Modifier
                .padding(paddingValues = it)
                .background(Color(0x89C5C1C2)),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            state = lazyColumnListState
        ) {
            item {
                Column(
                    Modifier
                        .background(Color.White)
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (post.photos.isNotEmpty())
                            AsyncImage(
                                model = ImageRequest.Builder(context = LocalContext.current)
                                    .data("http://d.wolf.16.fvds.ru" + post.photos[0].photo_path)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                        Text(post.blogTitle, style = MaterialTheme.typography.titleSmall)
                        if (post.created.isNotEmpty())
                            Text(
                                text = post.created.subSequence(0, 10).toString(),
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall
                            )
                    }
                    Text(
                        text = "${post.authorFirstName} ${post.authorLastName}",
                        color = Color.Gray
                    )
                    LazyRow(Modifier.fillMaxWidth()) {
                        items(post.categories.size) { i ->
                            PostType(type = post.categories[i].name)
                        }
                    }
                    Text(post.title, style = MaterialTheme.typography.titleLarge)
                    if (post.photos.isNotEmpty())
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data("http://d.wolf.16.fvds.ru" + post.photos[0].photo_path)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    Text(
                        post.text,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Image(
                                painterResource(id = ThemeR.drawable.thumb_up),
                                contentDescription = "upVote"
                            )
                            Text(post.likes.toString())
                            Image(
                                painterResource(id = ThemeR.drawable.thumb_down),
                                contentDescription = "downVote"
                            )
                        }
                    }
                }
            }
            items(state.comments.size) { i ->
                Comment(
                    state.comments[i],
                    onDownVoteClick = {
                        postViewModel.handle(PostIntent.CommentUpVote(state.comments[i].commentId))
                    }, onUpVoteClick = {
                        postViewModel.handle(PostIntent.CommentDownVote(state.comments[i].commentId))
                    })

            }
            when {
                state.isLoading -> item {
                    LoadingItem()
                }

                state.error != null -> item {
                    ErrorItem(message = "Some error occurred")
                }
            }
        }
    }

}

@Composable
fun Comment(
    comment: Comment,
    onUpVoteClick: () -> Unit,
    onDownVoteClick: () -> Unit,
) {
    Column(
        Modifier
            .background(Color.White)
            .padding(20.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (comment.avatar.isNotEmpty())
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("http://d.wolf.16.fvds.ru" + comment.avatar[0].photo_path)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            Text(
                text = "${comment.userFirstName} ${comment.userLastName}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = comment.created.subSequence(0, 10).toString(),
                color = Color.Gray,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(comment.commentText, style = MaterialTheme.typography.bodyMedium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(ThemeR.drawable.thumb_up),
                contentDescription = "likes",
                Modifier
                    .size(20.dp)
                    .clickable(onClick = onUpVoteClick)
            )
            Text(comment.likesCount.toString(), style = MaterialTheme.typography.bodyMedium)
            Image(
                painter = painterResource(ThemeR.drawable.thumb_down),
                contentDescription = "likes",
                Modifier
                    .size(20.dp)
                    .clickable(onClick = onDownVoteClick)
            )
        }
    }
}

@Composable
fun PostCommentBottomBar(
    userComment: String,
    onChangeUserComment: (String) -> Unit,
    onSendCommentClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            value = userComment,
            onValueChange = onChangeUserComment,
            placeholder = { Text("Add comment") },
            modifier = Modifier
                .weight(1f)
        )
        Image(
            imageVector = Icons.Default.Send, contentDescription = "send",
            modifier = Modifier
                .size(30.dp)
                .clickable(onClick = onSendCommentClick)
        )
    }

}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}

@Composable
fun ErrorItem(message: String) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box() {
            Text(
                color = Color.White,
                text = message,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun PostNavigationTopBar(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(10.dp)
    ) {
        Image(imageVector = Icons.Default.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .size(30.dp)
                .clickable { navController.navigate("home_screen") })
        Image(
            imageVector = Icons.Default.Search, contentDescription = "search",
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Composable
fun PostType(type: String) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(end = 5.dp, bottom = 5.dp)
            .height(25.dp),
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier.padding(5.dp),

            )
    }
}
