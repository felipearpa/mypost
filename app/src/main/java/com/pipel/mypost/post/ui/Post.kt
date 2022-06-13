package com.pipel.mypost.post.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.ui.theme.MyPostTheme

@Composable
private fun AppTopBar() {
    TopAppBar(title = { Text(text = "MyPost") }, elevation = 0.dp)
}

@Composable
fun PostView(viewModel: PostViewModel) {
    val posts by viewModel.postsFlow.collectAsState(initial = emptyList())
    val onItemClick = viewModel::navigateToPostDetail
    val onRefresh = viewModel::refresh

    Scaffold(
        topBar = { AppTopBar() }
    ) {
        PostList(posts = posts, onRefresh = onRefresh, onItemClick = onItemClick)
    }
}

@Composable
fun PostList(
    posts: List<PostModel>,
    onRefresh: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { onRefresh() }) {
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(posts) { post ->
                PostItem(post = post, onItemClick = onItemClick)
                Divider(modifier = Modifier.padding(top = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostListPreview() {
    MyPostTheme {
        PostList(posts = postsModelsForPreview(), onRefresh = {}, onItemClick = {})
    }
}