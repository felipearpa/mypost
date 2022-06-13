package com.felipearpa.mypost.post.detail.view

import com.felipearpa.mypost.comment.view.CommentModel
import com.felipearpa.mypost.post.view.PostModel
import com.felipearpa.mypost.user.view.UserModel

data class PostDetailModel(
    val post: PostModel,
    val author: UserModel,
    val comments: List<CommentModel>
)