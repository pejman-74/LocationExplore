package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.data.datastore.AppDatastore
import com.locationexplorer.data.datastore.AppDatastoreImpl
import com.locationexplorer.data.datastore.FakeAppDatastore
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton


@TestInstallIn(components = [SingletonComponent::class], replaces = [DataStoreModule::class])
@Module
object DataStoreModuleTest {
    @Provides
    @Singleton
    fun provideAppDataStore(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope,
        moshi: Moshi
    ): AppDatastore = FakeAppDatastore()
}