package com.pipel.mypost.post.data

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)