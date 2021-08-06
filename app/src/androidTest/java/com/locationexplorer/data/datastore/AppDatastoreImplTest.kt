package com.locationexplorer.data.datastore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.locationexplorer.data.model.share.SimpleLocation
import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class AppDatastoreImplTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instaTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var coroutineScope: CoroutineScope

    @Inject
    lateinit var moshi: Moshi

    private val testCoroutineScope get() = coroutineScope as TestCoroutineScope

    lateinit var appDatastore: AppDatastore

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        appDatastore = AppDatastoreImpl(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext,
            "TEST_DATASTORE",
            coroutineScope,
            moshi
        )
    }

    @After
    fun tearDown() {

        /**
         * clear data data store after each test
         * */
        runBlockingTest {
            appDatastore.clearDataStore()
        }
        testCoroutineScope.cancel()
        testCoroutineScope.cleanupTestCoroutines()
    }

    /**
     * when getUserLastLocation() called for first time should return empty Location
     * */
    @Test
    fun getUserLastLocation_should_return_empty_location() = runBlockingTest {
        assertThat(appDatastore.getUserLastLocation()).isEqualTo(SimpleLocation(0.0, 0.0))
    }

    /**
     * when sets the last user location should return that
     * */
    @Test
    fun getUserLastLocation_should_return_data() = runBlockingTest {
        val dummySimpleLocation = SimpleLocation(1.0, 1.0)
        appDatastore.setUserLastLocation(dummySimpleLocation)
        assertThat(appDatastore.getUserLastLocation()).isEqualTo(dummySimpleLocation)
    }

    /**
     * when getLastUpdateTime() called for first time should return 0
     * */
    @Test
    fun getLastUpdateTime_should_return_zero() = runBlockingTest {
        assertThat(appDatastore.getLastUpdateTime()).isEqualTo(0)
    }

    /**
     * when sets the lastUpdateTime should return that
     * */
    @Test
    fun lastUpdateTime_should_return_strode_data() = runBlockingTest {
        appDatastore.setLastUpdateTime(100)
        assertThat(appDatastore.getLastUpdateTime()).isEqualTo(100)
    }
}