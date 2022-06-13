package com.felipearpa.mypost.post.useCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemovePostsUseCase {

    suspend fun execute(postsIds: List<Int>)

}

class DefaultRemovePostsUseCase @Inject constructor(private val removePostUseCase: RemovePostUseCase) :
    RemovePostsUseCase {

    override suspend fun execute(postsIds: List<Int>): Unit = coroutineScope {
        withContext(Dispatchers.IO) {
            for (postId in postsIds) {
                removePostUseCase.execute(postId)
            }
        }
    }

}