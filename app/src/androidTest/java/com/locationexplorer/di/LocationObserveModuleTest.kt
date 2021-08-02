package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.util.location.FakeLocationObserver
import com.locationexplorer.util.location.LocationObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [LocationObserveModule::class])
@Module
object LocationObserveModuleTest {
    @Singleton
    @Provides
    fun provideLocationObserver(@ApplicationContext context: Context): LocationObserver =
        FakeLocationObserver()
}