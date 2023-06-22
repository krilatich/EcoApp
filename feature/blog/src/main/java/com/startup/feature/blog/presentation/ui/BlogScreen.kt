package com.startup.feature.blog.presentation.ui

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.startup.feature.blog.R
import com.startup.feature.blog.domain.entity.Blog
import com.startup.feature.blog.presentation.BlogIntent
import com.startup.feature.blog.presentation.BlogViewModel
import com.startup.shared.post.domain.entity.Post
import org.koin.androidx.compose.koinViewModel

@Composable
fun BlogScreen(
    navController: NavController,
    blogViewModel: BlogViewModel = koinViewModel(),
    blogId: String
) {
    blogViewModel.loadBlog("aeadca14-2d03-47ce-8862-2f0c04cb4197")
    val state by blogViewModel.uiState.collectAsState()

    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value)
            blogViewModel.handle(BlogIntent.LoadPosts)
    }

    Scaffold(bottomBar = { NavigationBottomBar() }, topBar = { NavigationTopBar(navController) }) {
        LazyColumn(
            state = lazyColumnListState,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            item {
                BlogTitle(state.blog)
            }
            items(state.posts.size) { i ->
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    Post(state.posts[i], onClick = {
                        navController.navigate("post_screen/${state.posts[i].id}")
                    }, onDownVoteClick = {
                        blogViewModel.handle(BlogIntent.UpVote(i))
                    }, onUpVoteClick = {
                        blogViewModel.handle(BlogIntent.DownVote(i))
                    })
                }
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
fun Post(
    post: Post,
    onClick: () -> Unit,
    onUpVoteClick: () -> Unit,
    onDownVoteClick: () -> Unit,
) {
    Card() {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("http://d.wolf.16.fvds.ru" + post.photos[0].photo_path)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )
                Text(post.blogTitle, style = MaterialTheme.typography.titleSmall)
                if (post.created.isNotEmpty())
                    Text(
                        post.created.subSequence(0, 10).toString(),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
            }
            Text(text = "${post.authorFirstName} ${post.authorLastName}", color = Color.Gray)
            LazyRow(Modifier.fillMaxWidth()) {
                items(post.categories.size) {
                    PostType(type = post.categories[it].name)
                }
            }
            Text(post.title, style = MaterialTheme.typography.titleLarge)
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("http://d.wolf.16.fvds.ru" + post.photos[0].photo_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            var postText = ""
            if (post.text.length > 100) postText = post.text.subSequence(0, 100).toString() + "..."
            Text(postText, color = Color.Gray,
                modifier = Modifier.clickable { onClick() })
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    /*
                    if (post. == LIKE)
                        Icon(
                            painterResource(id = ThemeR.drawable.thumb_up),
                            contentDescription = "upVote",
                            modifier = Modifier.clickable {
                                onUpVoteClick()
                            },
                            tint = MaterialTheme.colorScheme.primary
                        )
                    else

                     */
                    Icon(
                        painter = painterResource(id = com.startup.theme.R.drawable.thumb_up),
                        contentDescription = "upVote",
                        modifier = Modifier.clickable {
                            onUpVoteClick()
                        }
                    )
                    Text(post.likes.toString())
                    /*if (post.userReaction == DISLIKE)
                    Icon(
                        painterResource(id = ThemeR.drawable.thumb_down),
                        contentDescription = "downVote",
                        modifier = Modifier.clickable {
                            onDownVoteClick()
                        },
                        tint = MaterialTheme.colorScheme.primary
                    )
                else
                */
                    Icon(
                        painterResource(id = com.startup.theme.R.drawable.thumb_down),
                        contentDescription = "downVote",
                        modifier = Modifier.clickable {
                            onDownVoteClick()
                        })
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
        Image(
            imageVector = Icons.Default.Search, contentDescription = "search",
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Composable
fun NavigationBottomBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(imageVector = Icons.Default.Home, contentDescription = "home")
            Text("Home")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(id = R.drawable.question_answer), contentDescription = "chat")
            Text("Chat")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(imageVector = Icons.Default.Add, contentDescription = "add")
            Text("Create")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(imageVector = Icons.Default.Person, contentDescription = "profile")
            Text("Profile")
        }
    }
}

@Composable
fun BlogTitle(blog: Blog) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (blog.avatar.isNotEmpty())
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("http://d.wolf.16.fvds.ru" + blog.avatar[0].photo_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = CircleShape)
            )
        Column(
            Modifier
                .height(150.dp)
                .width(150.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                blog.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                blog.description,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
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
