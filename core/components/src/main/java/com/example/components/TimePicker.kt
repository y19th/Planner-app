package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTimePickerDialog(
    initHour: Int = 12,
    initMinute: Int = 0,
    onTimeChoose: (Int,Int) -> Unit,
    onDismiss: () -> Unit
) {

    val state = rememberTimePickerState(
        is24Hour = true
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Transparent
            )
            .clickable { onDismiss.invoke() }
        ,
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier

                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(all = 16.dp)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(
                state = state
            )

            VerticalSpacer(height = 12.dp)


            RoundedCoveringButton(
                modifier = Modifier
                    .padding(horizontal = 32.dp),
                onButtonClick = {
                    onTimeChoose.invoke(state.hour,state.minute)
                    onDismiss.invoke()
                }
            ) {
                Text(
                    text = "Ready"
                )
            }


            /*TextButton(
                onClick = {
                    onTimeChoose.invoke(state.hour, state.minute)
                }
            ) {
                *//*TODO remove literal*//*
                Text(
                    text = "ready"
                )
            }*/
        }
    }
}