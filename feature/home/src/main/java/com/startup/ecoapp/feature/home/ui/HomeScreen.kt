package com.startup.ecoapp.feature.home.ui

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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.startup.ecoapp.feature.home.R
import com.startup.ecoapp.feature.home.presentation.HomeIntent
import com.startup.ecoapp.feature.home.presentation.HomeViewModel
import com.startup.shared.post.domain.entity.Post
import org.koin.androidx.compose.koinViewModel
import com.startup.theme.R as ThemeR

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = koinViewModel()) {

    val state by homeViewModel.uiState.collectAsState()

    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyColumnListState.layoutInfo.totalItemsCount - 6)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value)
            homeViewModel.handle(HomeIntent.LoadPosts)
    }

    Scaffold(
        bottomBar = { NavigationBottomBar(navController = navController) },
        topBar = { NavigationTopBar(navController = navController) }) {
        LazyColumn(
            state = lazyColumnListState,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues = it)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            items(state.posts.size) { i ->
                Post(state.posts[i], onClick = {
                    navController.navigate("post_screen/${state.posts[i].id}")
                }, onDownVoteClick = {
                    homeViewModel.handle(HomeIntent.UpVote(state.posts[i].id))
                }, onUpVoteClick = {
                    homeViewModel.handle(HomeIntent.DownVote(state.posts[i].id))
                })

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
                    Icon(painterResource(id = ThemeR.drawable.thumb_up),
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
                        painterResource(id = ThemeR.drawable.thumb_down),
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
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)

    ) {
        Image(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "map",
            Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("map_screen")
                })
        Image(
            imageVector = Icons.Default.Search, contentDescription = "search", Modifier.size(30.dp)
        )
    }

}

@Composable
fun NavigationBottomBar(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "home",
                tint = MaterialTheme.colorScheme.primary
            )
            Text("Home")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(id = R.drawable.question_answer),
                contentDescription = "chat",
                modifier = Modifier
                    .clickable { navController.navigate("threads_screen") }
            )

            Text("Threads")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = Icons.Default.List, contentDescription = "blogs", modifier = Modifier
                    .clickable { navController.navigate("blogs_screen") }
            )
            Text("Blogs")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(imageVector = Icons.Default.Person, contentDescription = "profile")
            Text("Profile")
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


@Composable
@Preview
fun Preview() {
    HomeScreen(navController = rememberNavController())
}