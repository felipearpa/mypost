package com.felipearpa.mypost.post.detail.useCase

import com.felipearpa.mypost.comment.useCase.GetCommentsUseCase
import com.felipearpa.mypost.post.detail.domain.PostDetail
import com.felipearpa.mypost.post.useCase.GetPostUseCase
import com.felipearpa.mypost.user.useCase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
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
            val authorDeferred =
                async(context = Dispatchers.IO) { getUserUseCase.execute(userId = post.userId.value) }
            val commentsDeferred =
                async(context = Dispatchers.IO) { getCommentsUseCase.execute(postId = post.id.value) }
            return@coroutineScope PostDetail(
                post = post,
                author = authorDeferred.await(),
                comments = commentsDeferred.await()
            )
        }

}