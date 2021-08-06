package com.locationexplorer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.locationexplorer.R


@Composable
fun VenueAndLocationItem(
    name: String,
    address: String,
    distance: Int,
    onItemClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.primaryVariant.copy(0.5f))
            .clickable {
                onItemClick()
            }
            .padding(vertical = 12.dp)

    ) {
        val (nameRef, addressIconRef, distanceIconRef, addressRef, distanceRef) = createRefs()

        Text(
            modifier = Modifier.constrainAs(nameRef) {
                start.linkTo(parent.start, 8.dp)
                top.linkTo(parent.top)
                end.linkTo(distanceIconRef.start, 4.dp)
                width = Dimension.fillToConstraints
            },
            maxLines = 1,
            text = name, color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
        )
        Icon(
            modifier = Modifier.constrainAs(addressIconRef) {
                start.linkTo(nameRef.start)
                bottom.linkTo(parent.bottom)
            },
            imageVector = Icons.Filled.LocationOn,
            contentDescription = "item location"
        )
        Text(
            modifier = Modifier.constrainAs(addressRef) {
                start.linkTo(addressIconRef.end)
                top.linkTo(addressIconRef.top)
                bottom.linkTo(addressIconRef.bottom)
                end.linkTo(distanceRef.start, 4.dp)
                width = Dimension.fillToConstraints
            },
            text = address,
            maxLines = 1,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
        Icon(
            modifier = Modifier
                .size(40.dp)
                .constrainAs(distanceIconRef) {
                    end.linkTo(parent.end, 16.dp)
                    top.linkTo(parent.top)
                },
            painter = painterResource(id = R.drawable.ic_distance),
            contentDescription = "item location"
        )
        Text(
            modifier = Modifier.constrainAs(distanceRef) {
                start.linkTo(distanceIconRef.start)
                end.linkTo(distanceIconRef.end)
                top.linkTo(distanceIconRef.bottom)
            },
            text = distance.toString() + "M",
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
    }

}