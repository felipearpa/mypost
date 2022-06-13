package com.pipel.mypost.post.mapper

import com.pipel.mypost.post.data.PostResponse
import com.pipel.mypost.post.database.PostEntity
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

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