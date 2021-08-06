package com.locationexplorer.ui.screen.venuedetailscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.locationexplorer.data.model.response.venue_api.VenueResponse
import com.locationexplorer.data.repository.Repository
import com.locationexplorer.data.wapper.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class VenueDetailScreenViewModel @Inject constructor(
    private val repository: Repository,
    @Named("immediateMain") private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    val venueDetail: State<Resource<VenueResponse>> get() = _venueDetail
    private val _venueDetail = mutableStateOf<Resource<VenueResponse>>(Resource.Empty())

    fun getVenueDetail(venueId: String) = viewModelScope.launch(coroutineDispatcher) {
        if (venueId.isBlank())
            return@launch
        _venueDetail.value = Resource.Loading()
        _venueDetail.value = repository.getVenueDetail(venueId)
    }
}