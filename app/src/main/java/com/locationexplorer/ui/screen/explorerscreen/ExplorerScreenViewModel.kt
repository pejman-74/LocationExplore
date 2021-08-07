package com.locationexplorer.ui.screen.explorerscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locationexplorer.PAGE_SIZE
import com.locationexplorer.data.database.relation.VenueAndLocation
import com.locationexplorer.data.model.share.SimpleLocation
import com.locationexplorer.data.repository.Repository
import com.locationexplorer.data.wapper.LocationObserverStates
import com.locationexplorer.data.wapper.Resource
import com.locationexplorer.util.location.LocationObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ExplorerScreenViewModel @Inject constructor(
    private val repository: Repository,
    private val locationObserver: LocationObserver,
    @Named("immediateMain") private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _venueAndLocation =
        mutableStateOf<Resource<List<VenueAndLocation>>>(Resource.Empty())
    val venueAndLocation: State<Resource<List<VenueAndLocation>>> get() = _venueAndLocation

    private val _currentLocationResource =
        mutableStateOf<Resource<SimpleLocation>>(Resource.Empty())
    val currentLocationResource: State<Resource<SimpleLocation>> = _currentLocationResource

    private val _currentLocationObserveState = mutableStateOf<LocationObserverStates?>(null)
    val currentLocationObserveState: State<LocationObserverStates?> get() = _currentLocationObserveState

    private var lastSimpleLocation: SimpleLocation? = null

    private suspend fun explore(
        simpleLocation: SimpleLocation,
        offset: Int = 0,
        forceFetch: Boolean = false,
        isPaginationRequest: Boolean = false
    ) {
        repository.explore(
            simpleLocation,
            offset,
            forceFetch,
            isPaginationRequest
        ) { _offset, _totalResult ->
            repository.setOffset(_offset)
            repository.setTotalResult(_totalResult)

            repository.setLastLocation(simpleLocation)
            repository.setLastUpdate(System.currentTimeMillis())

        }.collect {
            _venueAndLocation.value = it
        }
    }

    //calculate next page index
    private fun calcNextPageOffset(offset: Int, totalResult: Int, pageSize: Int = PAGE_SIZE): Int =
        if (offset + pageSize <= totalResult)
            offset + pageSize
        else
            offset


    fun refreshLoad(simpleLocation: SimpleLocation) = viewModelScope.launch(mainDispatcher) {
        lastSimpleLocation = simpleLocation
        //load first page and if successfully loaded should clear previous data's in db
        explore(simpleLocation)
    }


    fun loadNextPage(simpleLocation: SimpleLocation? = lastSimpleLocation) =
        viewModelScope.launch(mainDispatcher) {

            if (simpleLocation == null)
                return@launch

            val nextPageOffset =
                calcNextPageOffset(repository.getOffset(), repository.getTotalResult())

            //check end reached
            if (nextPageOffset == repository.getOffset())
                return@launch

            //load next page and should NOT clear previous data's in db
            explore(
                simpleLocation = simpleLocation,
                offset = nextPageOffset,
                forceFetch = true,
                isPaginationRequest = true
            )
        }

    fun startObserveLocation() = viewModelScope.launch(mainDispatcher) {
        locationObserver.startObserve().collect { locationState ->
            _currentLocationObserveState.value = locationState
            if (locationState is LocationObserverStates.LocationChange)
                refreshLoad(locationState.simpleLocation)
        }
    }


    fun getCurrentLocation() = viewModelScope.launch(mainDispatcher) {
        _currentLocationResource.value = Resource.Loading()

        val currentLocationResources = locationObserver.currentLocation()
        _currentLocationResource.value = if (currentLocationResources == null)
            Resource.Error(throwable = Exception("can't get current location"))
        else
            Resource.Success(currentLocationResources)
    }

}