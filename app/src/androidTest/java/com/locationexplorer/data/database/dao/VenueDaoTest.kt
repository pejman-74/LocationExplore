package com.locationexplorer.data.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.location
import com.locationexplorer.data.database.AppDatabase
import com.venue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class VenueDaoTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instaTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    /**
     * when inserted a venue then getAllVenues() called should return that(check insertion)
     * */
    @Test
    fun t1() = runBlockingTest {
        appDatabase.venueDao().insert(venue)
        val venueAndLocation = appDatabase.venueDao().getAllVenues()
        assertThat(venueAndLocation.first()).isEqualTo(venue)
    }

    /**
     * when inserted a venue then clear() called should clear table in database
     * */
    @Test
    fun t2() = runBlockingTest {
        appDatabase.venueDao().insert(venue)
        appDatabase.venueDao().clear()
        val venueAndLocation = appDatabase.venueDao().getAllVenues()
        assertThat(venueAndLocation).isEmpty()
    }

    /**
     * check relation between venue and location in database
     * */
    @Test
    fun t3() = runBlockingTest {
        appDatabase.venueDao().insert(venue)
        appDatabase.locationDao().insert(location)
        val venueAndLocation = appDatabase.venueDao().getVenuesAndLocations().first()
        assertThat(venueAndLocation.first().venue).isEqualTo(venue)
        assertThat(venueAndLocation.first().location).isEqualTo(location)
    }
}