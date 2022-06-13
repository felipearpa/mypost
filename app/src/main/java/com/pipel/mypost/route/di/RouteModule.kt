package com.pipel.mypost.route.di

import com.pipel.mypost.route.DefaultRouteNavigator
import com.pipel.mypost.route.RouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RouteModule {

    @Provides
    @Singleton
    fun provideRouteNavigator(): RouteNavigator = DefaultRouteNavigator()

}