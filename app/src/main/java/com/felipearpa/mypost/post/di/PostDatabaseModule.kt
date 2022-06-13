package com.felipearpa.mypost.post.di

import android.content.Context
import androidx.room.Room
import com.felipearpa.mypost.post.database.PostRoomDatabase
import com.felipearpa.mypost.post.database.useCase.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDatabaseModule {

    @Singleton
    @Provides
    fun providePostDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        PostRoomDatabase::class.java,
        "post"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(database: PostRoomDatabase) = database.postDao()

}

@Module
@InstallIn(SingletonComponent::class)
interface PostDatabaseUseCaseModule {

    @Singleton
    @Binds
    fun provideAddPostDatabaseUseCase(impl: DefaultAddPostDatabaseUseCase): AddPostDatabaseUseCase

    @Singleton
    @Binds
    fun provideAddPostsDatabaseUseCase(impl: DefaultAddPostsDatabaseUseCase): AddPostsDatabaseUseCase

    @Singleton
    @Binds
    fun provideRemoveAllPostsDatabaseUseCase(impl: DefaultRemoveAllPostsDatabaseUseCase): RemoveAllPostsDatabaseUseCase

    @Singleton
    @Binds
    fun provideGetAllPostsDatabaseUseCase(impl: DefaultGetAllPostsDatabaseUseCase): GetAllPostsDatabaseUseCase

}