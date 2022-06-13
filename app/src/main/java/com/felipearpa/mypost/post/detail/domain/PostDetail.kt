package com.felipearpa.mypost.post.detail.domain

import com.felipearpa.mypost.comment.domain.Comment
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.user.domain.User

data class PostDetail(
    val post: Post,
    val author: User,
    val comments: List<Comment>
)