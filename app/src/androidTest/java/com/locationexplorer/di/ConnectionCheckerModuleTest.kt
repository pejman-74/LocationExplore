package com.locationexplorer.di

import android.content.Context
import com.locationexplorer.util.network.ConnectionChecker
import com.locationexplorer.util.network.FakeConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ConnectionCheckerModule::class]
)
@Module
object ConnectionCheckerModuleTest {

    @Singleton
    @Provides
    fun provideNetworkChecker(@ApplicationContext context: Context): ConnectionChecker =
        FakeConnectionChecker()

}