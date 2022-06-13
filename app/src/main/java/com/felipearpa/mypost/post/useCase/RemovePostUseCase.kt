package com.felipearpa.mypost.post.useCase

import com.felipearpa.mypost.post.data.PostRepository
import javax.inject.Inject

interface RemovePostUseCase {

    suspend fun execute(postId: Int)

}

class DefaultRemovePostUseCase @Inject constructor(private val postRepository: PostRepository) :
    RemovePostUseCase {

    override suspend fun execute(postId: Int) = postRepository.removePost(postId)

}