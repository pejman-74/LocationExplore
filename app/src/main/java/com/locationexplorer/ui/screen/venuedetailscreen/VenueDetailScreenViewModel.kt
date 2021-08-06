package com.locationexplorer.ui.screen.venuedetailscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locationexplorer.data.model.response.venue_api.VenueResponse
import com.locationexplorer.data.repository.Repository
import com.locationexplorer.data.wapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class VenueDetailScreenViewModel @Inject constructor(
    private val repository: Repository,
    @Named("immediateMain") private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    val venueDetailResource: State<Resource<VenueResponse>> get() = _venueDetailResource
    private val _venueDetailResource = mutableStateOf<Resource<VenueResponse>>(Resource.Empty())

    fun getVenueDetail(venueId: String) = viewModelScope.launch(coroutineDispatcher) {
        if (venueId.isBlank())
            return@launch
        _venueDetailResource.value = Resource.Loading()
        _venueDetailResource.value = repository.getVenueDetail(venueId)
    }
}