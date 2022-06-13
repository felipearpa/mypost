package com.pipel.mypost.post.ui

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
import com.pipel.mypost.R
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.ui.ProgressBar
import com.pipel.mypost.ui.gesturesDisabled
import com.pipel.mypost.ui.theme.MyPostTheme

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
    val onItemClick = viewModel::navigateToPostDetail
    val onRefresh = viewModel::refresh
    var mustConfirmRemoveAll by remember { mutableStateOf(false) }
    val onRemoveAll = { mustConfirmRemoveAll = true }
    val isLoading by viewModel.isLoadingFlow.collectAsState(initial = false)

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
            onItemClick = onItemClick,
            modifier = Modifier.gesturesDisabled(isLoading)
        )

        if (mustConfirmRemoveAll) {
            ConfirmRemoveAll(
                onDismissRequest = { mustConfirmRemoveAll = false },
                onConfirmRequest = {
                    mustConfirmRemoveAll = false
                    viewModel.removeAll()
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
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val swipeRefreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(state = swipeRefreshState, onRefresh = { onRefresh() }, modifier = modifier) {
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