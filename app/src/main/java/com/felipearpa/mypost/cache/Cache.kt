package com.felipearpa.mypost.cache

interface Cache<TModel> {

    suspend fun load()
    suspend fun reload()

}