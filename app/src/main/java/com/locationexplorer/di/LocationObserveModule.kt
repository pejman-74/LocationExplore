package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.util.location.LocationObserver
import com.locationexplorer.util.location.LocationObserverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocationObserveModule {

    @Singleton
    @Provides
    fun provideLocationObserver(@ApplicationContext context: Context): LocationObserver =
        LocationObserverImpl(context)
}