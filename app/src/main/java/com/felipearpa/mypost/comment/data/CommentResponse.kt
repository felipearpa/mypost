package com.felipearpa.mypost.comment.data

data class CommentResponse(
    val postId: Int,
    val id: Int,
    val email: String,
    val body: String
)