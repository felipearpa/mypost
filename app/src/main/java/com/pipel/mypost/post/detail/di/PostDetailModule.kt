package com.pipel.mypost.post.detail.di

import com.pipel.mypost.post.detail.useCase.DefaultGetPostDetailUseCase
import com.pipel.mypost.post.detail.useCase.GetPostDetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PostDetailUseCaseModule {

    @Binds
    @Singleton
    fun provideGetPostDetailUseCase(impl: DefaultGetPostDetailUseCase): GetPostDetailUseCase

}