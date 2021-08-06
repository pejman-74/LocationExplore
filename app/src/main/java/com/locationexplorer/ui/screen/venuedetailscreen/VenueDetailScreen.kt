package com.locationexplorer.ui.screen.venuedetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.locationexplorer.R
import com.locationexplorer.data.model.response.venue_api.VenueResponse
import com.locationexplorer.data.wapper.Resource
import com.locationexplorer.util.exception.NoInternetException

@Composable
fun VenueDetailScreen(
    venueId: String,
    venueDetailScreenViewModel: VenueDetailScreenViewModel = hiltViewModel(),
    showSnackBar: suspend (message: String) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    val venueDetail by remember { venueDetailScreenViewModel.venueDetailResource }

    LaunchedEffect(key1 = Unit) {
        venueDetailScreenViewModel.getVenueDetail(venueId)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = navigateUp) {
                    Icon(Icons.Filled.ArrowBack, "Back")
                }
                Text(
                    text = "Location Detail",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 8.dp)
                )

            }
        }
        if (venueDetail is Resource.Success) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentScale = ContentScale.Crop,
                painter = rememberImagePainter(getVenueResponseBestPhotoUrlMediumSize(venueDetail.data)),
                contentDescription = "Location best picture"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                venueDetail.data?.response?.venue?.let { venue ->
                    venue.name?.let {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append("Name:")
                            }
                            append(it)
                        })
                    }
                    venue.categories?.first()?.name?.let {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append("Category:")
                            }
                            append(it)
                        })
                    }
                    venue.location?.address?.let {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append("Address:")
                            }
                            append(it)
                        })
                    }
                    venue.contact?.phone?.let {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append("Phone:")
                            }
                            append(it)
                        })
                    }
                    venue.likes?.count?.let {
                        Text(text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append("Like count:")
                            }
                            append(it.toString())
                        })
                    }
                    venue.shortUrl?.let {
                        Text(text = buildAnnotatedString {

                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                append("Short link:")
                            }
                            append(it)
                        })
                    }
                }
            }
        }
    }
    LaunchedEffect(key1 = venueDetail) {
        if (venueDetail is Resource.Error) {
            if (venueDetail.throwable is NoInternetException)
                showSnackBar(context.getString(R.string.no_connection_error))
            else
                showSnackBar(
                    venueDetail.throwable?.message
                        ?: context.getString(R.string.unknown_api_error)
                )
        }
    }
}

fun getVenueResponseBestPhotoUrlMediumSize(venueResponse: VenueResponse?): String? {
    return venueResponse?.response?.venue?.bestPhoto?.let {
        it.prefix + "width500" + it.suffix
    }
}