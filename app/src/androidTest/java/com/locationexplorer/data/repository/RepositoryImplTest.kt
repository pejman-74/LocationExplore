package com.locationexplorer.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.*
import com.google.common.truth.Truth.assertThat
import com.locationexplorer.data.api.ExploreApi
import com.locationexplorer.data.api.FakeExploreApi
import com.locationexplorer.data.api.FakeVenueDetailApi
import com.locationexplorer.data.api.VenueDetailApi
import com.locationexplorer.data.database.AppDatabase
import com.locationexplorer.data.datastore.AppDatastore
import com.locationexplorer.data.wapper.Resource
import com.locationexplorer.util.network.ConnectionChecker
import com.locationexplorer.util.network.FakeConnectionChecker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryImplTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val instaTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var appDatastore: AppDatastore

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var connectionChecker: ConnectionChecker

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var exploreApi: ExploreApi

    @Inject
    lateinit var venueDetailApi: VenueDetailApi

    private val fakeExploreApi: FakeExploreApi
        get() = exploreApi as FakeExploreApi

    private val fakeVenueDetailApi:FakeVenueDetailApi get() = venueDetailApi as FakeVenueDetailApi
    private val fakeConnectionChecker: FakeConnectionChecker
        get() = connectionChecker as FakeConnectionChecker


    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @After
    fun tearDown() {

    }

    /**
     * when explore() called with paginationRequest=false should
     * return data and delete previews data's in db
     * */
    @Test
    fun explore_paginationRequestFalse() = runBlockingTest {
        var resources =
            repository.explore(simpleLocation, isPaginationRequest = false).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(1)
        //api should return next page
        fakeExploreApi.currentJson = secondPageJson
        resources = repository.explore(simpleLocation, isPaginationRequest = false).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(1)
    }

    /**
     * when explore() called with paginationRequest=true should
     * return data WITHOUT delete previews data's in db
     * */
    @Test
    fun explore_paginationRequestTrue() = runBlockingTest {
        var resources =
            repository.explore(simpleLocation, isPaginationRequest = false).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(1)
        //api should return next page
        fakeExploreApi.currentJson = secondPageJson
        resources = repository.explore(simpleLocation, isPaginationRequest = true).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(2)
    }

    /**
     * when explore() called with empty Database should return data
     * */
    @Test
    fun explore_shouldFetchIfDatabaseIsEmpty() = runBlockingTest {
        //make other shouldFetch conditions to false
        fakeConnectionChecker.isHaveConnection = false
        appDatastore.setLastUpdateTime(0)
        appDatastore.setUserLastLocation(simpleLocation)

        val resources =
            repository.explore(simpleLocation, currentTime = 0).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(1)
    }

    /**
     * when explore() called with force fetch should return data
     * */
    @Test
    fun explore_shouldFetchIfForceFetchIsTrue() = runBlockingTest {

        //make other shouldFetch conditions to false
        fakeConnectionChecker.isHaveConnection = false
        appDatabase.venueDao().insert(venue)
        appDatabase.locationDao().insert(location)
        appDatastore.setLastUpdateTime(0)
        appDatastore.setUserLastLocation(simpleLocation)

        val resources =
            repository.explore(simpleLocation, forceFetch = true, currentTime = 0).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(1)
    }

    /**
     * when explore() called with Connected connection should return data
     * */
    @Test
    fun explore_shouldFetchOnConnectionAvailable() = runBlockingTest {
        fakeConnectionChecker.isHaveConnection = true

        //make other shouldFetch conditions to false
        appDatabase.venueDao().insert(venue)
        appDatabase.locationDao().insert(location)
        appDatastore.setLastUpdateTime(0)
        appDatastore.setUserLastLocation(simpleLocation)

        val resources =
            repository.explore(simpleLocation, forceFetch = false, currentTime = 0).take(2).toList()
        assertThat(resources.last().data?.size).isEqualTo(1)
    }

    /**
     * when explore() called old data's should return updated data
     * */
    @Test
    fun explore_shouldFetchIfDataIsOld() = runBlockingTest {


        //make other shouldFetch conditions to false
        appDatabase.venueDao().insert(venue)
        appDatabase.locationDao().insert(location)
        fakeConnectionChecker.isHaveConnection = false
        appDatastore.setUserLastLocation(simpleLocation)

        val resources =
            repository.explore(simpleLocation, forceFetch = false).take(2).toList()
        //check data's updated or not
        assertThat(resources.last().data?.first()?.venue?.id).isEqualTo(firstPageVenueId)
    }

    /**
     * when explore() called old data's should return updated data
     * */
    @Test
    fun explore_shouldFetchIfLocationChanged() = runBlockingTest {
        //make other shouldFetch conditions to false
        appDatabase.venueDao().insert(venue)
        appDatabase.locationDao().insert(location)
        fakeConnectionChecker.isHaveConnection = false

        val resources =
            repository.explore(simpleLocation, forceFetch = false).take(2).toList()
        //check data's updated or not
        assertThat(resources.last().data?.size).isEqualTo(1)
    }

    /**
     * when explore() called with all conditions is false should return cached data
     * */
    @Test
    fun explore_shouldNOTFetchOnAllConditionsIsFalse() = runBlockingTest {


        //make All shouldFetch conditions to false
        appDatabase.venueDao().insert(venue)
        appDatabase.locationDao().insert(location)
        fakeConnectionChecker.isHaveConnection = false
        appDatastore.setLastUpdateTime(0)
        appDatastore.setUserLastLocation(simpleLocation)

        val resources =
            repository.explore(simpleLocation, forceFetch = false, currentTime = 0).take(2).toList()
        assertThat(resources.last().data?.first()?.venue).isEqualTo(venue)
        assertThat(resources.last().data?.first()?.location).isEqualTo(location)
    }

    /**
     * when explore() called add api throw exception should return [Resource.Error]
     * */
    @Test
    fun explore_shouldReturnErrorType() = runBlockingTest {
        fakeExploreApi.shouldThrowIoException = true
        val resources =
            repository.explore(simpleLocation).take(2).toList()
        assertThat(resources.last() is Resource.Error).isTrue()
    }

    /**
     * when getVenueDetail() called should return [Resource.Success]
     * */
    @Test
    fun getVenueDetail_shouldReturnSuccessResult()= runBlockingTest {
        val resources =
            repository.getVenueDetail("")
        assertThat(resources is Resource.Success).isTrue()
    }

    /**
     * when getVenueDetail() called with ioException should return [Resource.Error]
     * */
    @Test
    fun getVenueDetail_shouldReturnErrorResult()= runBlockingTest {
        fakeVenueDetailApi.shouldThrowIoException=true
        val resources =
            repository.getVenueDetail("")
        assertThat(resources is Resource.Error).isTrue()
    }
}