package com.pipel.mypost.post.database.useCase

import com.pipel.mypost.post.database.PostDao
import javax.inject.Inject

interface RemoveAllPostsDatabaseUseCase {

    suspend fun execute()

}

class DefaultRemoveAllPostsDatabaseUseCase @Inject constructor(private val postDao: PostDao) :
    RemoveAllPostsDatabaseUseCase {

    override suspend fun execute() = postDao.removeAll()

}