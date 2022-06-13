package com.pipel.mypost.cache

interface Cache<TModel> {

    suspend fun load()
    suspend fun reload()

}