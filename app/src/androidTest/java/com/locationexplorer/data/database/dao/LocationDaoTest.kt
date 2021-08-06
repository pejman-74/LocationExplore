package com.locationexplorer.data.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.location
import com.locationexplorer.data.database.AppDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class LocationDaoTest {

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
     * when inserted a location then getAllLocations() called should return that(check insertion)
     * */
    @Test
    fun getAllLocations_should_return_data() = runBlockingTest {
        appDatabase.locationDao().insert(location)
        val locations = appDatabase.locationDao().getAllLocations()
        Truth.assertThat(locations.first()).isEqualTo(location)
    }

    /**
     * when inserted a location then clear() called should clear table in database
     * */
    @Test
    fun clear_should_clear_data() = runBlockingTest {
        appDatabase.locationDao().insert(location)
        appDatabase.locationDao().clear()
        val locations = appDatabase.locationDao().getAllLocations()
        Truth.assertThat(locations).isEmpty()
    }

}