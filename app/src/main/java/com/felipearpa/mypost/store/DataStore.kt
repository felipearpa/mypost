package com.felipearpa.mypost.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

interface DataStore {

    suspend fun putCacheDateTime(cacheDateTime: Date?)

    fun getCacheDateTimeFlow(): Flow<Date?>

}

class DefaultDataStore constructor(private val context: Context) : DataStore {

    private val Context.preferences by preferencesDataStore("preferences")
    private val cacheDateTimeKey = stringPreferencesKey("cacheDateTime")

    override suspend fun putCacheDateTime(cacheDateTime: Date?) {
        context.preferences.edit { preferences ->
            preferences[cacheDateTimeKey] =
                cacheDateTime?.time?.toString() ?: ""
        }
    }

    override fun getCacheDateTimeFlow(): Flow<Date?> =
        context.preferences.data.map { preferences ->
            val cacheDateTimeValue = preferences[cacheDateTimeKey]
            val cacheDateTimeInMillis =
                if (cacheDateTimeValue != null && cacheDateTimeValue != "") cacheDateTimeValue.toLong() else null
            return@map if (cacheDateTimeInMillis != null) Date(cacheDateTimeInMillis) else null
        }

}