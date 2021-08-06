package com.locationexplorer.ui.screen.explorerscreen

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.locationexplorer.R
import com.locationexplorer.data.wapper.LocationObserverStates
import com.locationexplorer.data.wapper.Resource
import com.locationexplorer.ui.component.DialogBuilder
import com.locationexplorer.ui.component.ExplorerScreenTopAppBar
import com.locationexplorer.ui.component.VenueAndLocationItem
import com.locationexplorer.util.exception.NoInternetException


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ExplorerScreen(
    explorerScreenViewModel: ExplorerScreenViewModel,
    showSnackBar: suspend (message: String) -> Unit,
    navToDetailScreen: (venueId: String) -> Unit
) {
    val venueAndLocation by remember { explorerScreenViewModel.venueAndLocation }
    val currentLocationResource by remember { explorerScreenViewModel.currentLocationResource }
    val currentLocationObserverStates by remember { explorerScreenViewModel.currentLocationObserveState }
    val fineLocationPermissionState = rememberPermissionState(ACCESS_FINE_LOCATION)
    val context = LocalContext.current
    val activity = (context as? Activity)

    var topAppBarStatus by remember { mutableStateOf(context.getString(R.string.default_top_appbar)) }
    var isTopAppBarAnimating by remember { mutableStateOf(true) }


    when {
        fineLocationPermissionState.hasPermission -> {
            LaunchedEffect(key1 = Unit) {
                explorerScreenViewModel.getCurrentLocation()
                explorerScreenViewModel.startObserveLocation()
            }
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                ExplorerScreenTopAppBar(
                    topAppBarStatus = topAppBarStatus,
                    isTopAppBarAnimating = isTopAppBarAnimating
                ) {
                    explorerScreenViewModel.getCurrentLocation()
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    venueAndLocation.data?.let { list ->
                        itemsIndexed(list) { index, item ->
                            VenueAndLocationItem(
                                item.venue.name,
                                item.location.formattedAddress.toString(),
                                item.location.distance
                            ) {
                                navToDetailScreen(item.venue.id)
                            }
                            LaunchedEffect(key1 = Unit) {
                                if (index == list.lastIndex - 3) {
                                    explorerScreenViewModel.loadNextPage()
                                }
                            }
                        }
                    }

                }
            }
            LaunchedEffect(key1 = venueAndLocation) {
                if (venueAndLocation is Resource.Error) {
                    if (venueAndLocation.throwable is NoInternetException)
                        showSnackBar(context.getString(R.string.no_connection_error))
                    else
                        showSnackBar(
                            venueAndLocation.throwable?.message
                                ?: context.getString(R.string.unknown_api_error)
                        )
                }
            }
            LaunchedEffect(key1 = currentLocationResource) {
                if (currentLocationResource is Resource.Loading) {
                    topAppBarStatus = context.getString(R.string.finding_location_top_appbar)
                    isTopAppBarAnimating = true
                }

                if (currentLocationResource is Resource.Success) {
                    explorerScreenViewModel.refreshLoad(currentLocationResource.data!!)
                    topAppBarStatus = context.getString(R.string.default_top_appbar)
                    isTopAppBarAnimating = false
                }
            }
            if (currentLocationResource is Resource.Error) {
                topAppBarStatus = context.getString(R.string.finding_location_failed_top_appbar)
                isTopAppBarAnimating = false

                if (venueAndLocation.data.isNullOrEmpty() &&
                    currentLocationObserverStates is LocationObserverStates.ProviderDisabled
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        DialogBuilder(
                            message = context.getString(R.string.location_request_message),
                            positiveButtonText = context.getString(R.string.location_positive_button),
                            negativeButtonText = context.getString(R.string.location_negative_button),
                            onPositiveButtonClick = {
                                activity?.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                            },
                        ) {
                            activity?.finish()
                        }
                    }
                }
            }

        }
        fineLocationPermissionState.shouldShowRationale ||
                !fineLocationPermissionState.permissionRequested -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                DialogBuilder(message = context.getString(R.string.permission_request_message),
                    positiveButtonText = context.getString(R.string.permission_positive_button),
                    negativeButtonText = context.getString(R.string.permission_negative_button),
                    onPositiveButtonClick = {
                        fineLocationPermissionState.launchPermissionRequest()
                    }) {
                    activity?.finish()
                }

            }
        }
        else -> {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null)
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity?.startActivity(intent)
        }

    }

}

