package com.felipearpa.mypost.post.database.useCase

import com.felipearpa.mypost.post.database.PostDao
import com.felipearpa.mypost.post.domain.Post
import com.felipearpa.mypost.post.mapper.PostMapper
import javax.inject.Inject

interface GetAllPostsDatabaseUseCase {

    suspend fun execute(): List<Post>

}

class DefaultGetAllPostsDatabaseUseCase @Inject constructor(private val postDao: PostDao) :
    GetAllPostsDatabaseUseCase {

    override suspend fun execute(): List<Post> =
        postDao.getAll().map(PostMapper::mapFromDatabaseToDomain)

}