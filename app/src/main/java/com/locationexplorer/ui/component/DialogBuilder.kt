package com.locationexplorer.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DialogBuilder(
    message: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.primaryVariant.copy(0.5f),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = message,
            maxLines = 2,
            lineHeight = 25.sp,
            style = MaterialTheme.typography.h6.copy(
                fontSize = 18.sp,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = {
                    onPositiveButtonClick()

                },
                elevation = null
            ) {
                Text(positiveButtonText)
            }
            Spacer(Modifier.width(32.dp))
            Button(onClick = {
                onNegativeButtonClick()
            }, elevation = null) {
                Text(negativeButtonText)
            }
        }
    }


}