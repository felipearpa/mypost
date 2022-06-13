package com.pipel.mypost.post.database.useCase

import com.pipel.mypost.post.database.PostDao
import com.pipel.mypost.post.domain.Post
import com.pipel.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface GetAllPostsDatabaseUseCase {

    suspend fun execute(): List<Post>

}

class DefaultGetAllPostsDatabaseUseCase @Inject constructor(private val postDao: PostDao) :
    GetAllPostsDatabaseUseCase {

    override suspend fun execute(): List<Post> =
        postDao.getAll().map(PostMapper::mapFromDatabaseToDomain)

}