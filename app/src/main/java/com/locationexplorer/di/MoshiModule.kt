package com.locationexplorer.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MoshiModule {
    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()
}