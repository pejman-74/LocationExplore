package com.locationexplorer.di

import com.locationexplorer.data.api.ExploreApi
import com.locationexplorer.data.api.VenueDetailApi
import com.locationexplorer.data.database.AppDatabase
import com.locationexplorer.data.datastore.AppDatastore
import com.locationexplorer.data.holder.IndexHolder
import com.locationexplorer.data.repository.Repository
import com.locationexplorer.data.repository.RepositoryImpl
import com.locationexplorer.util.network.ConnectionChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        exploreApi: ExploreApi,
        venueDetailApi: VenueDetailApi,
        appDatabase: AppDatabase,
        appDatastore: AppDatastore,
        connectionChecker: ConnectionChecker,
        indexHolder: IndexHolder
    ): Repository =
        RepositoryImpl(
            exploreApi,
            venueDetailApi,
            appDatabase,
            appDatastore,
            connectionChecker,
            indexHolder
        )
}