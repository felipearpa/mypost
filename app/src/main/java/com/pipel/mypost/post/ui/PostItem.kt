package com.pipel.mypost.post.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.ui.theme.MyPostTheme

@Composable
fun PostItem(post: PostModel, onItemClick: (Int) -> Unit) {
    Text(text = post.title, modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemClick(post.id)
        })
}

@Preview(showBackground = true)
@Composable
private fun PoolLayoutItemPreview() {
    MyPostTheme {
        PostItem(post = postsModelsForPreview().iterator().next(), onItemClick = {})
    }
}