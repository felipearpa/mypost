package com.felipearpa.mypost.post.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.felipearpa.mypost.R
import com.felipearpa.mypost.post.view.PostModel
import com.felipearpa.mypost.ui.theme.MyPostTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostItem(post: PostModel, onClick: () -> Unit, onRemove: () -> Unit) {
    val dismissState = rememberDismissState(
        confirmStateChange = { dismissValue ->
            if (dismissValue == DismissValue.DismissedToStart) {
                onRemove()
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier.fillMaxWidth(),
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(fraction = 0.5f) },
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.error)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                            contentDescription = "Remove"
                        )
                    }
                }
            }
        }) {
        Text(text = post.title, modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .clickable {
                onClick()
            })
    }
}

@Preview(showBackground = true)
@Composable
private fun PoolLayoutItemPreview() {
    MyPostTheme {
        PostItem(
            post = postsModelsForPreview().iterator().next(),
            onClick = {},
            onRemove = {})
    }
}