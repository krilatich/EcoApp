package com.startup.ecoapp.feature.post.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.startup.ecoapp.feature.post.R
import com.startup.theme.R as ThemeR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(postViewModel: PostViewModel = viewModel(), navController: NavController) {

	val state by postViewModel.uiState.collectAsState()
	val post = state.post

	Scaffold(
		bottomBar = { PostNavigationBottomBar(navController) },
		topBar = { PostNavigationTopBar(navController) })
	{
		LazyColumn(
			Modifier
				.padding(paddingValues = it)
				.background(Color(0x89C5C1C2)),
			verticalArrangement = Arrangement.spacedBy(10.dp)
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
						Image(
							painterResource(id = state.post.avatarId),
							contentDescription = "avatar",
							modifier = Modifier
								.size(40.dp)
						)
						Text(post.author, style = MaterialTheme.typography.titleSmall)
						Text(post.time, color = Color.Gray)
					}
					Row()
					{
						for (type in post.types) {
							PostType(type = type)
						}
					}
					Text(post.header, style = MaterialTheme.typography.titleLarge)
					Image(
						painterResource(id = post.imageId),
						contentDescription = "postImage",
						modifier = Modifier
							.fillMaxWidth()
					)
					Text(post.text,
						color = Color.Black,
						style = MaterialTheme.typography.bodyLarge)
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
							Text(post.upVote.toString())
							Image(
								painterResource(id = ThemeR.drawable.thumb_down),
								contentDescription = "downVote"
							)
						}
					}
				}
			}
			items(state.comments.size) { i ->
				Comment(comment = state.comments[i])
			}
		}
	}

}

@Composable
fun Comment(comment: Comment) {
	Column(
		Modifier
			.background(Color.White)
			.padding(20.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(10.dp)
		) {
			Image(
				painterResource(id = comment.author.avatarId),
				contentDescription = "avatar",
				modifier = Modifier
					.size(20.dp)
			)
			Text(
				comment.author.nickname,
				style = MaterialTheme.typography.bodySmall
			)
			Text(
				comment.time,
				color = Color.Gray,
				style = MaterialTheme.typography.bodySmall
			)
		}
		Text(comment.text, style = MaterialTheme.typography.bodyMedium)
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(10.dp)
		) {
			Image(
				imageVector = Icons.Default.ThumbUp,
				contentDescription = "likes",
				Modifier.size(20.dp)
			)
			Text(comment.likes.toString(), style = MaterialTheme.typography.bodyMedium)
		}
	}
}

@Composable
fun PostNavigationBottomBar(navController: NavController) {
	var comment by remember { mutableStateOf("") }
	Row(
		Modifier
			.fillMaxWidth()
			.padding(20.dp),
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(10.dp)
	) {
		TextField(
			value = comment,
			onValueChange = { comment = it },
			placeholder = { Text("Add comment") },
			modifier = Modifier
				.weight(1f)
		)
		Image(
			imageVector = Icons.Default.Face, contentDescription = "smile",
			modifier = Modifier
				.size(30.dp)
		)
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


@Composable
@Preview
fun Pr() {
	PostScreen(navController = rememberNavController())
}
