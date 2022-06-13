package com.pipel.mypost.post.mapper

import com.pipel.mypost.post.data.PostResponse
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.view.PostModel
import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

object PostMapper {

    fun mapFromDataToDomain(postResponse: PostResponse): Post {
        return Post(
            userId = Identifier(postResponse.userId),
            id = Identifier(postResponse.id),
            title = NonEmptyString(postResponse.title),
            body = NonEmptyString(postResponse.body)
        )
    }

    fun mapFromDomainToView(post: Post): PostModel {
        return PostModel(
            userId = post.userId.value,
            id = post.id.value,
            title = post.title.value,
            body = post.body.value
        )
    }

}