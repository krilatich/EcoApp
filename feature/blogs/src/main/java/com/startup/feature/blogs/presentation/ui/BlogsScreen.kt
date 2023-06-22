package com.startup.feature.blogs.presentation.ui

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.startup.feature.blogs.R
import com.startup.feature.blogs.domain.entity.Blog
import com.startup.feature.blogs.presentation.BlogsIntent
import com.startup.feature.blogs.presentation.BlogsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BlogsScreen(
    navController: NavController,
    blogViewModel: BlogsViewModel = koinViewModel(),
) {
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
            blogViewModel.handle(BlogsIntent.LoadBlogs)
    }

    Scaffold(
        bottomBar = {
            NavigationBottomBar(
                navController = navController,
                onCreateButtonClick = { blogViewModel.handle(BlogsIntent.OpenDialog) })
        },
        topBar = { NavigationTopBar(navController) }) { paddingValues ->
        LazyColumn(
            state = lazyColumnListState,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            items(state.blogs.size) { i ->
                Box(Modifier.padding(start = 20.dp, end = 20.dp)) {
                    Blog(state.blogs[i], onClick = {
                        navController.navigate("blog_screen/${state.blogs[i].blogId}")
                    }, onSubscribeButton = {
                        blogViewModel.handle(BlogsIntent.SubscribeBlog)
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
    if (state.createDialogOpen)
        CreateBlogDialog(
            blogTitle = state.userBlogTitle,
            onTitleChange = { blogViewModel.handle(BlogsIntent.ChangeBlogTitle(it)) },
            blogDescription = state.userBlogDescription,
            onDescriptionChange = { blogViewModel.handle(BlogsIntent.ChangeBlogDescription(it)) },
            onCreateButtonClick = { blogViewModel.handle(BlogsIntent.CreateBlog) }
        )
}

@Composable
fun Blog(
    blog: Blog,
    onClick: () -> Unit,
    onSubscribeButton: () -> Unit,
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
                        .data("http://d.wolf.16.fvds.ru" + blog.avatar[0].photo_path)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(shape = CircleShape)
                )
                Text(blog.title, style = MaterialTheme.typography.titleSmall)
            }
            Text(text = "${blog.authorFirstName} ${blog.authorLastName}", color = Color.Gray)
            Button(onClick = {}) {
                Text("Subscribe")
            }
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("http://d.wolf.16.fvds.ru" + blog.avatar[0].photo_path)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            var blogDesc = ""
            if (blog.description.length > 100) blogDesc =
                blog.description.subSequence(0, 100).toString() + "..."
            Text(blogDesc, color = Color.Gray,
                modifier = Modifier.clickable { onClick() })
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
fun NavigationBottomBar(navController: NavController, onCreateButtonClick: () -> Unit) {
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
            Image(
                imageVector = Icons.Default.Add, contentDescription = "add", modifier = Modifier
                    .clickable(onClick = onCreateButtonClick)
            )
            Text("Create")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(imageVector = Icons.Default.Person, contentDescription = "profile")
            Text("Profile")
        }
    }
}


@Composable
fun CreateBlogDialog(
    blogTitle: String,
    onTitleChange: (String) -> Unit,
    blogDescription: String,
    onDescriptionChange: (String) -> Unit,
    onCreateButtonClick: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .background(Color.Black.copy(alpha = 0.5f))
                .fillMaxSize()
                .clickable { }
        ) {
        }
        Card(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Column(
                Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Blog Title")
                OutlinedTextField(
                    value = blogTitle,
                    onValueChange = onTitleChange,
                    singleLine = true
                )
                Text("Description")
                OutlinedTextField(
                    value = blogDescription,
                    onValueChange = onDescriptionChange,
                    modifier = Modifier.height(150.dp)
                )
                Button(onClick = onCreateButtonClick) {
                    Text("Create")
                }
            }
        }
    }
}
