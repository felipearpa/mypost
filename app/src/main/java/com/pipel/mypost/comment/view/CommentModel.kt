package com.pipel.mypost.comment.view

data class CommentModel(
    val postId: Int,
    val id: Int,
    val email: String,
    val body: String
)