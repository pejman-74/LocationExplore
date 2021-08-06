package com.locationexplorer.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.locationexplorer.data.model.share.SimpleLocation
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppDatastoreImpl(
    context: Context,
    datastoreName: String,
    coroutineScope: CoroutineScope,
    moshi: Moshi
) : AppDatastore {

    private val Context.appDatastore: DataStore<Preferences> by preferencesDataStore(
        name = datastoreName,
        scope = coroutineScope
    )


    private val appDatastore = context.appDatastore

    private val simpleLocationAdapter = moshi.adapter(SimpleLocation::class.java)

    companion object {

        private val LAST_USER_LOCATION_KEY = stringPreferencesKey("LAST_USER_LOCATION_KEY")
        private val LAST_UPDATE_TIME = longPreferencesKey("LAST_UPDATE_TIME")
    }

    override suspend fun setUserLastLocation(simpleLocation: SimpleLocation) {
        //convert SimpleLocation to string
        val stringSimpleLocation = simpleLocationAdapter.toJson(simpleLocation)
        appDatastore.edit { preferences ->
            preferences[LAST_USER_LOCATION_KEY] = stringSimpleLocation
        }
    }

    override suspend fun getUserLastLocation(): SimpleLocation =
        appDatastore.data.map { preferences ->
            val lastLocationString = preferences[LAST_USER_LOCATION_KEY]

            lastLocationString ?: return@map SimpleLocation(0.0, 0.0)

            //convert string to SimpleLocation
            runCatching {
                simpleLocationAdapter.fromJson(lastLocationString)
            }.getOrNull() ?: SimpleLocation(0.0, 0.0)

        }.first()


    override suspend fun setLastUpdateTime(time: Long) {
        appDatastore.edit { preferences ->
            preferences[LAST_UPDATE_TIME] = time
        }
    }

    override suspend fun getLastUpdateTime(): Long = appDatastore.data.map { preferences ->
        preferences[LAST_UPDATE_TIME] ?: 0
    }.first()

    override suspend fun clearDataStore() {
        appDatastore.edit { it.clear() }
    }

}