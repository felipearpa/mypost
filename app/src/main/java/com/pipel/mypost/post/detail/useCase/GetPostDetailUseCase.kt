package com.pipel.mypost.post.detail.useCase

import com.pipel.mypost.comment.useCase.GetCommentsUseCase
import com.pipel.mypost.post.detail.domain.PostDetail
import com.pipel.mypost.post.useCase.GetPostUseCase
import com.pipel.mypost.user.useCase.GetUserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

interface GetPostDetailUseCase {

    suspend fun execute(postId: Int): PostDetail

}

class DefaultGetPostDetailUseCase @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getCommentsUseCase: GetCommentsUseCase
) : GetPostDetailUseCase {

    override suspend fun execute(postId: Int): PostDetail =
        coroutineScope {
            val post = getPostUseCase.execute(postId = postId)
            val authorDeferred = async { getUserUseCase.execute(userId = post.userId.value) }
            val commentsDeferred = async { getCommentsUseCase.execute(postId = post.id.value) }
            return@coroutineScope PostDetail(
                post = post,
                author = authorDeferred.await(),
                comments = commentsDeferred.await()
            )
        }

}