package com.locationexplorer.di

import com.locationexplorer.data.holder.IndexHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StaticsModule {
    @Provides
    @Singleton
    fun provideIndexHolder(): IndexHolder = IndexHolder()
}