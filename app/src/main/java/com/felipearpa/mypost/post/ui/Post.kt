package com.felipearpa.mypost.post.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.felipearpa.mypost.R
import com.felipearpa.mypost.post.view.PostModel
import com.felipearpa.mypost.ui.ProgressBar
import com.felipearpa.mypost.ui.gesturesDisabled
import com.felipearpa.mypost.ui.theme.MyPostTheme

@Composable
private fun AppTopBar(onRemoveAll: () -> Unit) {
    TopAppBar(
        title = { Text(text = "MyPost") },
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { onRemoveAll() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                    contentDescription = "Remove all"
                )
            }
        })
}

@Composable
fun PostView(viewModel: PostViewModel) {
    val posts by viewModel.postsFlow.collectAsState(initial = emptyList())
    val onClickItem = viewModel::navigateToPostDetail
    val onRefresh = viewModel::refresh
    var mustConfirmRemoveAll by remember { mutableStateOf(false) }
    val onRemoveAll = { mustConfirmRemoveAll = true }
    val isLoading by viewModel.isLoadingFlow.collectAsState(initial = false)
    val onRemoveItem = viewModel::removePost

    Scaffold(
        topBar = { AppTopBar(onRemoveAll = onRemoveAll) }
    ) {
        if (isLoading) {
            ProgressBar(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
        }

        PostList(
            posts = posts,
            onRefresh = onRefresh,
            onClickItem = onClickItem,
            onRemoveItem = onRemoveItem,
            modifier = Modifier.gesturesDisabled(isLoading)
        )

        if (mustConfirmRemoveAll) {
            ConfirmRemoveAll(
                onDismissRequest = { mustConfirmRemoveAll = false },
                onConfirmRequest = {
                    mustConfirmRemoveAll = false
                    viewModel.removeAllPosts()
                })
        }
    }
}

@Composable
private fun ConfirmRemoveAll(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = { onDismissRequest() })
                { Text(text = "Cancel") }

                TextButton(
                    onClick = { onConfirmRequest() })
                { Text(text = "OK") }
            }
        },
        text = {
            Text(text = "Are you sure to remove all posts?")
        })
}

@Composable
fun PostList(
    posts: List<PostModel>,
    onRefresh: () -> Unit,
    onClickItem: (Int) -> Unit,
    onRemoveItem: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val swipeRefreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { onRefresh() }, modifier = modifier) {
        LazyColumn(contentPadding = PaddingValues(8.dp)) {
            items(posts) { post ->
                key(post.id) {
                    PostItem(
                        post = post,
                        onClick = { onClickItem(post.id) },
                        onRemove = { onRemoveItem(post.id) })
                    Divider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PostListPreview() {
    MyPostTheme {
        PostList(
            posts = postsModelsForPreview(),
            onRefresh = {},
            onClickItem = {},
            onRemoveItem = {})
    }
}