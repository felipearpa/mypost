package com.pipel.mypost.post.useCase

import com.pipel.mypost.post.data.PostRepository
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface GetPostUseCase {

    suspend fun execute(postId: Int): Post

}

class DefaultGetPostUseCase @Inject constructor(private val postRepository: PostRepository) :
    GetPostUseCase {

    override suspend fun execute(postId: Int): Post =
        PostMapper.mapFromDataToDomain(postRepository.getPost(postId = postId))

}