package com.example.planner_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planner_app.R
import com.example.planner_app.presentation.components.VerticalSpacer
import com.example.planner_app.ui.theme.Surface
import com.example.planner_app.ui.theme.successVariant

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.setting_screen),
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.successVariant,
                    shape = RoundedCornerShape(topStart = 30.dp,topEnd = 30.dp)
                )
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {

        }
    }
}

@Preview(
    showSystemUi = true,
    backgroundColor = 0xFF020035
)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}