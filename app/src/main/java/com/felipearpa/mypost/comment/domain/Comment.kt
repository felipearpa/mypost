package com.felipearpa.mypost.comment.domain

import com.felipearpa.mypost.type.Identifier
import com.felipearpa.mypost.type.NonEmptyString

data class Comment(
    val postId: Identifier,
    val id: Identifier,
    val email: NonEmptyString,
    val body: NonEmptyString
)