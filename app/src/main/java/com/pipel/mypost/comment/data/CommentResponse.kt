package com.pipel.mypost.comment.data

data class CommentResponse(
    val postId: Int,
    val id: Int,
    val email: String,
    val body: String
)