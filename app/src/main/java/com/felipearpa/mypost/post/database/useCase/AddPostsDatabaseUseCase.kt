package com.felipearpa.mypost.post.database.useCase

import com.felipearpa.mypost.post.domain.Post
import javax.inject.Inject

interface AddPostsDatabaseUseCase {

    suspend fun execute(posts: List<Post>)

}

class DefaultAddPostsDatabaseUseCase @Inject constructor(private val addPostDatabaseUseCase: AddPostDatabaseUseCase) :
    AddPostsDatabaseUseCase {

    override suspend fun execute(posts: List<Post>) {
        for (post in posts) {
            addPostDatabaseUseCase.execute(post)
        }
    }

}