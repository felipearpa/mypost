package com.felipearpa.mypost.comment.di

import com.felipearpa.mypost.comment.data.CommentRepository
import com.felipearpa.mypost.comment.useCase.DefaultGetCommentsUseCase
import com.felipearpa.mypost.comment.useCase.GetCommentsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CommentUseCaseModule {

    @Binds
    @Singleton
    fun provideGetCommentsUseCase(impl: DefaultGetCommentsUseCase): GetCommentsUseCase

}

@Module
@InstallIn(SingletonComponent::class)
object CommentRepositoryModule {

    @Provides
    @Singleton
    fun provideCommentRepository(retrofit: Retrofit): CommentRepository =
        retrofit.create(CommentRepository::class.java)

}