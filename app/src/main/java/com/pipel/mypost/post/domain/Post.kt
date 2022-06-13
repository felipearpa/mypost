package com.pipel.mypost.post.domain

import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

data class Post(
    val userId: Identifier,
    val id: Identifier,
    val title: NonEmptyString,
    val body: NonEmptyString
)