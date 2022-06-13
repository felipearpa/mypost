package com.pipel.mypost.comment.useCase

import com.pipel.mypost.comment.data.CommentRepository
import com.pipel.mypost.comment.domain.Comment
import com.pipel.mypost.comment.mapper.CommentMapper
import javax.inject.Inject

interface GetCommentsUseCase {

    suspend fun execute(postId: Int): List<Comment>

}

class DefaultGetCommentsUseCase @Inject constructor(private val commentRepository: CommentRepository) :
    GetCommentsUseCase {

    override suspend fun execute(postId: Int): List<Comment> =
        commentRepository.getComments(postId = postId).map(CommentMapper::mapFromDataToDomain)

}