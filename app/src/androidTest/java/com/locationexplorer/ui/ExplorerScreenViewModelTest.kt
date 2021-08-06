package com.locationexplorer.ui

import com.google.common.truth.Truth.assertThat
import com.locationexplorer.PAGE_SIZE
import com.locationexplorer.data.api.ExploreApi
import com.locationexplorer.data.database.AppDatabase
import com.locationexplorer.data.datastore.AppDatastore
import com.locationexplorer.data.holder.IndexHolder
import com.locationexplorer.data.repository.Repository
import com.locationexplorer.data.wapper.LocationObserverStates
import com.locationexplorer.data.wapper.Resource
import com.locationexplorer.ui.screen.explorerscreen.ExplorerScreenViewModel
import com.locationexplorer.util.location.FakeLocationObserver
import com.locationexplorer.util.location.LocationObserver
import com.simpleLocation
import com.totalResult
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class ExplorerScreenViewModelTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatastore: AppDatastore

    @Inject
    lateinit var appDatabase: AppDatabase

    @Inject
    lateinit var repository: Repository

    @Inject
    @Named("immediateMain")
    lateinit var mainCoroutineDispatcher: CoroutineDispatcher

    @Inject
    lateinit var exploreApi: ExploreApi

    @Inject
    lateinit var indexHolder: IndexHolder

    @Inject
    lateinit var locationObserver: LocationObserver

    private val fakeLocationObserver get() = locationObserver as FakeLocationObserver

    lateinit var viewModel: ExplorerScreenViewModel


    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        viewModel =
            ExplorerScreenViewModel(repository, fakeLocationObserver, mainCoroutineDispatcher)
    }

    @After
    fun tearDown() {
    }


    /**
     * when refreshLoad Called should return data
     * */
    @Test
    fun refreshLoad_shouldReturnData() = runBlockingTest {
        viewModel.refreshLoad(simpleLocation)
        val results = viewModel.venueAndLocation.value
        assertThat(results.data?.size).isEqualTo(1)
    }


    /**
     * when refreshLoad called should set indexes
     * */
    @Test
    fun refreshLoad_shouldSetIndexes() = runBlockingTest {
        viewModel.refreshLoad(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(0)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)
    }

    /**
     * when loadNextPage Called should return data
     * */
    @Test
    fun loadNextPage_shouldReturnData() = runBlockingTest {
        viewModel.refreshLoad(simpleLocation)
        viewModel.loadNextPage(simpleLocation)
        val results = viewModel.venueAndLocation.value
        assertThat(results.data?.size).isEqualTo(1)
    }

    /**
     * when loadNextPage without set lastLocation should not return data
     * */
    @Test
    fun loadNextPage_shouldNotReturnData() = runBlockingTest {
        viewModel.loadNextPage()
        val results = viewModel.venueAndLocation.value
        assertThat(results.data).isNull()
    }

    /**
     * when loadNextPage called should set indexes
     * */
    @Test
    fun loadNextPage_shouldSetIndexes() = runBlockingTest {
        viewModel.refreshLoad(simpleLocation)
        viewModel.loadNextPage(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(20)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)
    }

    /**
     * when loadNextPage called multiple times should set indexes correctly
     * sample:
     * totalResults=78
     * pageSize=20
     *
     *      0-20-40-60-60
     *
     *last index repeated because smaller than page size
     * */
    @Test
    fun loadNextPage_callMultipleTimeShouldSetIndexesCorrectly() = runBlockingTest {

        viewModel.refreshLoad(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(0)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)

        viewModel.loadNextPage(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(PAGE_SIZE)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)

        viewModel.loadNextPage(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(PAGE_SIZE * 2)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)

        viewModel.loadNextPage(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(PAGE_SIZE * 3)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)

        //last index repeated because smaller than page size
        viewModel.loadNextPage(simpleLocation)
        assertThat(repository.getOffset()).isEqualTo(PAGE_SIZE * 3)
        assertThat(repository.getTotalResult()).isEqualTo(totalResult)
    }

    /**
     * when startObserveLocation() called should return data in venueAndLocations
     * */
    @Test
    fun startObserveLocation() = runBlockingTest {
        viewModel.startObserveLocation()
        assertThat(viewModel.venueAndLocation.value.data?.size).isEqualTo(1)
    }
    /**
     * when startObserveLocation() called should set currentLocationObserveState
     * */
    @Test
    fun startObserveLocation_set_currentLocationObserveState() = runBlockingTest {
        viewModel.startObserveLocation()
        assertThat(viewModel.currentLocationObserveState.value is LocationObserverStates.LocationChange).isTrue()
    }

    /**
     * when getCurrentLocation() called should return current success location in currentLocationResource
     * */
    @Test
    fun getCurrentLocation_Success() = runBlockingTest {
        viewModel.getCurrentLocation()
        assertThat(viewModel.currentLocationResource.value is Resource.Success).isTrue()
    }

    /**
     * when getCurrentLocation() called with null response should return current Error in currentLocationResource
     * */
    @Test
    fun getCurrentLocation_Fail() = runBlockingTest {
        fakeLocationObserver.currentLocation=null
        viewModel.getCurrentLocation()
        assertThat(viewModel.currentLocationResource.value is Resource.Error).isTrue()
    }

}