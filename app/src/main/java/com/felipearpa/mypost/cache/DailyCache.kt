package com.felipearpa.mypost.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import java.util.*

class DailyCache<TModel>(
    private val onLoad: (TModel) -> Unit,
    private val cacheDateTimeFlow: Flow<Date?>,
    private val numberOfDays: Int,
    private val loadFromService: suspend () -> TModel,
    private val loadFromDatabase: suspend () -> TModel,
    private val cache: suspend (TModel) -> Unit
) : Cache<TModel> {

    override suspend fun load() {
        cacheDateTimeFlow.collectLatest { cacheDateTime ->
            if (mustLoadFromService(cacheDateTime)) {
                val items = loadFromService()
                onLoad(items)
                cache(items)
            } else {
                onLoad(loadFromDatabase())
            }
        }
    }

    override suspend fun reload() {
        onLoad(loadFromService())
    }

    private fun mustLoadFromService(cacheDateTime: Date?): Boolean =
        cacheDateTime == null || isCacheDateTimeGreaterThanOneDay(cacheDateTime)

    private fun isCacheDateTimeGreaterThanOneDay(cacheDateTime: Date) =
        (Date().time - cacheDateTime.time) / 1000 / 60 / 60 / 24 > numberOfDays

}