package com.locationexplorer.di

import com.locationexplorer.data.api.ExploreApi
import com.locationexplorer.data.api.FakeExploreApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [RemoteApiModule::class])
@Module
object RemoteApiModuleTest {
    @Singleton
    @Provides
    fun provideExploreApi(moshi: Moshi): ExploreApi = FakeExploreApi(moshi)
}