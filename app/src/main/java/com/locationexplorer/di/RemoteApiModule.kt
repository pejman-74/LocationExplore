package com.locationexplorer.di

import com.locationexplorer.data.api.ExploreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteApiModule {
    @Singleton
    @Provides
    fun provideExploreApi(retrofit: Retrofit): ExploreApi = retrofit.create(ExploreApi::class.java)
}