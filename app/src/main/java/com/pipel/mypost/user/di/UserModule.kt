package com.pipel.mypost.user.di

import com.pipel.mypost.user.data.UserRepository
import com.pipel.mypost.user.useCase.DefaultGetUserUseCase
import com.pipel.mypost.user.useCase.GetUserUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserUseCaseModule {

    @Binds
    @Singleton
    fun provideGetUserUseCase(impl: DefaultGetUserUseCase): GetUserUseCase

}

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(retrofit: Retrofit): UserRepository =
        retrofit.create(UserRepository::class.java)

}