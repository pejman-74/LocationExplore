package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.DATASTORE_NAME
import com.locationexplorer.data.datastore.AppDatastore
import com.locationexplorer.data.datastore.AppDatastoreImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {
    @Provides
    @Singleton
    fun provideAppDataStore(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope,
        moshi: Moshi
    ): AppDatastore = AppDatastoreImpl(context, DATASTORE_NAME,coroutineScope, moshi)
}