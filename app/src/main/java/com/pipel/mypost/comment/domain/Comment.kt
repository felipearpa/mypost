package com.pipel.mypost.comment.domain

import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

data class Comment(
    val postId: Identifier,
    val id: Identifier,
    val email: NonEmptyString,
    val body: NonEmptyString
)