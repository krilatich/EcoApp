package com.example.thread.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.thread.presentation.ThreadIntent
import com.example.threads.presentation.ThreadViewModel
import com.startup.shared.comment.domain.entity.Comment
import org.koin.androidx.compose.koinViewModel

@Composable
fun ThreadScreen(
    navController: NavController,
    threadsViewModel: ThreadViewModel = koinViewModel(),
    threadId: String
) {
    threadsViewModel.threadId = threadId
    threadsViewModel.handle(ThreadIntent.LoadThread)
    threadsViewModel.handle(ThreadIntent.LoadComments)

    val state by threadsViewModel.uiState.collectAsState()

    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value)
            threadsViewModel.handle(ThreadIntent.LoadComments)
    }

    Scaffold(
        bottomBar = {
            NavigationBottomBar(
                userComment = state.userComment,
                onSendCommentClick = { threadsViewModel.handle(ThreadIntent.CreateComment) },
                onChangeUserComment = { threadsViewModel.handle(ThreadIntent.ChangeUserComment(it)) }
            )
        },
        topBar = { NavigationTopBar(navController) }) { paddingValues ->
        LazyColumn(
            state = lazyColumnListState,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            items(state.comments.size) { i ->
                Comment(comment = state.comments[i])
            }
            when {
                state.error != null -> item {
                    ErrorItem(message = "Some error occurred")
                }

                state.isLoading -> item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingItem()
                    }
                }
            }
        }

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
fun NavigationTopBar(navController: NavController) {
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
        Text(
            "Threads",
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium
        )
        Image(
            imageVector = Icons.Default.Search, contentDescription = "search",
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Composable
fun NavigationBottomBar(
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
fun Comment(
    comment: Comment
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
    }
}


