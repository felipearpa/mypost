package com.felipearpa.mypost.post.domain

import com.felipearpa.mypost.type.Identifier
import com.felipearpa.mypost.type.NonEmptyString

data class Post(
    val userId: Identifier,
    val id: Identifier,
    val title: NonEmptyString,
    val body: NonEmptyString,
    val isFavorite: Boolean
)