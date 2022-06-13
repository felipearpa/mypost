package com.pipel.mypost.post.useCase

import com.pipel.mypost.post.data.PostRepository
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.mapper.PostMapper
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