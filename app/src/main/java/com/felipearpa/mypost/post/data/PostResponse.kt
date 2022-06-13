package com.felipearpa.mypost.post.data

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)