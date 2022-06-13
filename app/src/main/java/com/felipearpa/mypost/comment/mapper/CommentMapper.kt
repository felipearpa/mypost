package com.felipearpa.mypost.comment.mapper

import com.felipearpa.mypost.comment.data.CommentResponse
import com.felipearpa.mypost.comment.domain.Comment
import com.felipearpa.mypost.comment.view.CommentModel
import com.felipearpa.mypost.type.Identifier
import com.felipearpa.mypost.type.NonEmptyString

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