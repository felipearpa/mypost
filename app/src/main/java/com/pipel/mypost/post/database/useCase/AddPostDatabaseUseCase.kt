package com.pipel.mypost.post.database.useCase

import com.pipel.mypost.post.database.PostDao
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface AddPostDatabaseUseCase {

    suspend fun execute(post: Post)

}

class DefaultAddPostDatabaseUseCase @Inject constructor(private val postDao: PostDao) :
    AddPostDatabaseUseCase {

    override suspend fun execute(post: Post) =
        postDao.add(post = PostMapper.mapFromDomainToDatabase(post))

}