package com.felipearpa.mypost.post.useCase

import com.felipearpa.mypost.post.data.PostRepository
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface GetPostUseCase {

    suspend fun execute(postId: Int): Post

}

class DefaultGetPostUseCase @Inject constructor(private val postRepository: PostRepository) :
    GetPostUseCase {

    override suspend fun execute(postId: Int): Post =
        PostMapper.mapFromDataToDomain(postRepository.getPost(postId = postId))

}