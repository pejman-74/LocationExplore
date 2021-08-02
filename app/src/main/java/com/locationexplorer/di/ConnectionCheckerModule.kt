package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.util.network.ConnectionChecker
import com.locationexplorer.util.network.ConnectionCheckerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ConnectionCheckerModule {
    @Singleton
    @Provides
    fun provideNetworkChecker(@ApplicationContext context: Context): ConnectionChecker =
        ConnectionCheckerImpl(context)
}