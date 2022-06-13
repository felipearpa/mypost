package com.pipel.mypost.post.detail.domain

import com.pipel.mypost.comment.domain.Comment
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.user.domain.User

data class PostDetail(
    val post: Post,
    val author: User,
    val comments: List<Comment>
)