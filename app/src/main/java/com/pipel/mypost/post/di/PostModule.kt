package com.pipel.mypost.post.di

import com.pipel.mypost.post.data.PostRepository
import com.pipel.mypost.post.useCase.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PoolLayoutUseCaseModule {

    @Binds
    @Singleton
    fun provideGetPostsUseCase(impl: DefaultGetPostsUseCase): GetPostsUseCase

    @Binds
    @Singleton
    fun provideGetPostUseCase(impl: DefaultGetPostUseCase): GetPostUseCase

    @Binds
    @Singleton
    fun provideRemovePostUseCase(impl: DefaultRemovePostUseCase): RemovePostUseCase

    @Binds
    @Singleton
    fun provideRemovePostsUseCase(impl: DefaultRemovePostsUseCase): RemovePostsUseCase

}

@Module
@InstallIn(SingletonComponent::class)
object PoolLayoutRepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(retrofit: Retrofit): PostRepository =
        retrofit.create(PostRepository::class.java)

}