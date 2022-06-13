package com.pipel.mypost.comment.mapper

import com.pipel.mypost.comment.data.CommentResponse
import com.pipel.mypost.comment.domain.Comment
import com.pipel.mypost.comment.view.CommentModel
import com.pipel.mypost.type.Identifier
import com.pipel.mypost.type.NonEmptyString

object CommentMapper {

    fun mapFromDataToDomain(commentResponse: CommentResponse): Comment =
        Comment(
            postId = Identifier(commentResponse.postId),
            id = Identifier(commentResponse.id),
            email = NonEmptyString(commentResponse.email),
            body = NonEmptyString(commentResponse.body)
        )

    fun mapFromDomainToView(comment: Comment): CommentModel =
        CommentModel(
            postId = comment.postId.value,
            id = comment.id.value,
            email = comment.email.value,
            body = comment.body.value
        )

}