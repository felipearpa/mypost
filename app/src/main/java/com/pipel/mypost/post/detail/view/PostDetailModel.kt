package com.pipel.mypost.post.detail.view

import com.pipel.mypost.comment.view.CommentModel
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.user.view.UserModel

data class PostDetailModel(
    val post: PostModel,
    val author: UserModel,
    val comments: List<CommentModel>
)