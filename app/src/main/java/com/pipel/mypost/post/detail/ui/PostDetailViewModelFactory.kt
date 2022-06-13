package com.pipel.mypost.post.detail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@AssistedFactory
interface PostDetailViewModelFactory {

    fun create(postId: Int): PostDetailViewModel

}

fun providePostDetailViewModelFactory(
    assistedFactory: PostDetailViewModelFactory,
    postId: Int
): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(postId = postId) as T
        }

    }