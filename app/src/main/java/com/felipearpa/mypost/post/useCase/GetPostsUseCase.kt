package com.felipearpa.mypost.post.useCase

import com.felipearpa.mypost.post.data.PostRepository
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface GetPostsUseCase {

    suspend fun execute(): List<Post>

}

class DefaultGetPostsUseCase @Inject constructor(private val postRepository: PostRepository) :
    GetPostsUseCase {

    override suspend fun execute(): List<Post> {
        return postRepository.getPosts().map(PostMapper::mapFromDataToDomain)
    }

}