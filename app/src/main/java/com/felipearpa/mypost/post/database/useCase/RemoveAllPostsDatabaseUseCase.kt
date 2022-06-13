package com.felipearpa.mypost.post.database.useCase

import com.felipearpa.mypost.post.database.PostDao
import javax.inject.Inject

interface RemoveAllPostsDatabaseUseCase {

    suspend fun execute()

}

class DefaultRemoveAllPostsDatabaseUseCase @Inject constructor(private val postDao: PostDao) :
    RemoveAllPostsDatabaseUseCase {

    override suspend fun execute() = postDao.removeAll()

}