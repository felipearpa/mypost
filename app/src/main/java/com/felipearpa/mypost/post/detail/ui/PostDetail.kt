package com.felipearpa.mypost.post.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.felipearpa.mypost.R
import com.felipearpa.mypost.comment.view.CommentModel
import com.felipearpa.mypost.post.detail.view.PostDetailModel
import com.felipearpa.mypost.user.view.UserModel

@Composable
private fun AppTopBar(onNavigation: () -> Unit) {
    TopAppBar(
        title = { Text(text = "MyPost") },
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { onNavigation() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "Back to posts"
                )
            }
        })
}

@Composable
fun PostDetailView(viewModel: PostDetailViewModel) {
    val postDetail by viewModel.postDetailFlow.collectAsState(initial = null)
    val onNavigationClick = viewModel::navigateUp

    Scaffold(
        topBar = { AppTopBar(onNavigation = onNavigationClick) }
    ) {
        postDetail?.let { nonNullPostDetail ->
            PostDetail(postDetail = nonNullPostDetail)
        }
    }
}

@Composable
fun PostDetail(postDetail: PostDetailModel) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Title(title = postDetail.post.title)
        }

        item {
            Body(body = postDetail.post.body)
        }

        item {
            Author(author = postDetail.author)
        }

        item {
            CommentList(comments = postDetail.comments)
        }
    }
}

@Composable
private fun Title(title: String) {
    Text(text = title, style = MaterialTheme.typography.h4)
}

@Composable
private fun Body(body: String) {
    Text(text = body)
}

@Composable
private fun Author(author: UserModel) {
    Column {
        Text(text = "Author", style = MaterialTheme.typography.h6)
        Text(text = author.name)
        Text(text = author.email)
        Text(text = author.phone)
        Text(text = author.website)
    }
}

@Composable
private fun CommentList(comments: List<CommentModel>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Comments", style = MaterialTheme.typography.h6)
        Divider()
        for (comment in comments) {
            CommentItem(comment = comment)
            Divider()
        }
    }
}

@Composable
private fun CommentItem(comment: CommentModel) {
    Text(text = comment.body, style = MaterialTheme.typography.body1)
    Text(text = comment.email, style = MaterialTheme.typography.body2)
}