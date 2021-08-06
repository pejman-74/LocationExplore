package com.locationexplorer.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp


@Composable
fun ExplorerScreenTopAppBar(
    topAppBarStatus: String,
    isTopAppBarAnimating: Boolean,
    onRefreshClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()

    val topAppBarStatusAnimStatus = if (isTopAppBarAnimating)
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        ).value
    else
        1f

    TopAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = topAppBarStatus,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .animateContentSize()
                    .padding(start = 8.dp)
                    .alpha(if (isTopAppBarAnimating) topAppBarStatusAnimStatus else 1f)
            )
            IconButton(onClick = onRefreshClick) {
                Icon(Icons.Filled.Refresh, "Refresh")
            }
        }
    }

}
