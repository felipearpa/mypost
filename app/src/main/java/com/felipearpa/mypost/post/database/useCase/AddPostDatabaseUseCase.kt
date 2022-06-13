package com.felipearpa.mypost.post.database.useCase

import com.felipearpa.mypost.post.database.PostDao
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface AddPostDatabaseUseCase {

    suspend fun execute(post: Post)

}

class DefaultAddPostDatabaseUseCase @Inject constructor(private val postDao: PostDao) :
    AddPostDatabaseUseCase {

    override suspend fun execute(post: Post) =
        postDao.add(post = PostMapper.mapFromDomainToDatabase(post))

}