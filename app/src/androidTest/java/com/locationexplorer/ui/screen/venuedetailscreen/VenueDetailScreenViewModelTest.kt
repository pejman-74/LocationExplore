package com.locationexplorer.ui.screen.venuedetailscreen

import com.google.common.truth.Truth.assertThat
import com.locationexplorer.data.repository.Repository
import com.locationexplorer.data.wapper.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@OptIn(ExperimentalCoroutinesApi::class)
class VenueDetailScreenViewModelTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    @Named("immediateMain")
    lateinit var mainCoroutineDispatcher: CoroutineDispatcher

    @Inject
    lateinit var repository: Repository

    lateinit var viewModel: VenueDetailScreenViewModel

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        viewModel =
            VenueDetailScreenViewModel(repository, mainCoroutineDispatcher)
    }

    /**
     * when getVenueDetail called with blank venue id should not api called
     * */
    @Test
    fun getVenueDetail_withBlankId() = runBlockingTest {
        viewModel.getVenueDetail("")
        assertThat(viewModel.venueDetail.value is Resource.Empty).isTrue()
    }

    /**
     * when getVenueDetail called with correct venue id should api called
     * */
    @Test
    fun getVenueDetail_withCorrectId() = runBlockingTest {
        viewModel.getVenueDetail("11")
        assertThat(viewModel.venueDetail.value is Resource.Success).isTrue()
    }
}