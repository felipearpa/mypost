package com.felipearpa.mypost.store.di

import android.content.Context
import com.felipearpa.mypost.store.DataStore
import com.felipearpa.mypost.store.DefaultDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Singleton
    @Provides
    fun provideStore(@ApplicationContext app: Context): DataStore = DefaultDataStore(app)

}