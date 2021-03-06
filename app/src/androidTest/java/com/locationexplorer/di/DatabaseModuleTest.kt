package com.locationexplorer.di

import android.content.Context
import androidx.room.Room
import com.locationexplorer.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
@Module
object DatabaseModuleTest {
    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        coroutineDispatcher: TestCoroutineDispatcher
    ): AppDatabase =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .setTransactionExecutor(coroutineDispatcher.asExecutor())
            .setQueryExecutor(coroutineDispatcher.asExecutor()).allowMainThreadQueries()
            .build()
}

