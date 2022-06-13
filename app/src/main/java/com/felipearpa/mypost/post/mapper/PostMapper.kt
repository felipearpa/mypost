package com.felipearpa.mypost.post.mapper

import com.felipearpa.mypost.post.data.PostResponse
import com.felipearpa.mypost.post.database.PostEntity
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.post.view.PostModel
import com.felipearpa.mypost.type.Identifier
import com.felipearpa.mypost.type.NonEmptyString

object PostMapper {

    fun mapFromDataToDomain(postResponse: PostResponse): Post =
        Post(
            userId = Identifier(postResponse.userId),
            id = Identifier(postResponse.id),
            title = NonEmptyString(postResponse.title),
            body = NonEmptyString(postResponse.body),
            isFavorite = false
        )

    fun mapFromDomainToView(post: Post): PostModel =
        PostModel(
            userId = post.userId.value,
            id = post.id.value,
            title = post.title.value,
            body = post.body.value
        )

    fun mapFromDomainToDatabase(post: Post): PostEntity =
        PostEntity(
            userId = post.userId.value,
            id = post.id.value,
            title = post.title.value,
            body = post.body.value,
            isFavorite = post.isFavorite
        )

    fun mapFromDatabaseToDomain(postEntity: PostEntity): Post =
        Post(
            userId = Identifier(postEntity.userId),
            id = Identifier(postEntity.id),
            title = NonEmptyString(postEntity.title),
            body = NonEmptyString(postEntity.body),
            isFavorite = false
        )

}