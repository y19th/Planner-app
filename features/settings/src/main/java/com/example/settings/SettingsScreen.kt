package com.example.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.components.DropDownTextField
import com.example.components.RoundedCoveringButton
import com.example.components.VerticalSpacer
import com.example.settings.components.SettingsPart
import com.example.settings.models.ThemeSetting
import com.example.ui.R

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.setting_screen),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(vertical = 24.dp, horizontal = 20.dp)
        ) {
            SettingsPart(title = stringResource(id = R.string.settings_theme)) {
                DropDownTextField(
                    value = stringResource(id = ThemeSetting.Light.nameId),
                    labelText = stringResource(id = R.string.setting_title_theme),
                    dropDownItemsList = receiveThemeListValues(),
                    onDropDownClick = { /*TODO*/ },
                    onValueChange = {

                    }
                )

                VerticalSpacer(height = 8.dp)

                DropDownTextField(
                    value = "Red color",
                    labelText = "Color",
                    onDropDownClick = { /*TODO*/ },
                    onValueChange = {

                    }
                )
            }

            VerticalSpacer(height = 16.dp)
            
            SettingsPart(title = stringResource(id = R.string.settings_caching)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(id = R.string.setting_cache_size),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )   
                    Text(
                        text = stringResource(id = R.string.setting_cache_format, 52.4f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

                VerticalSpacer(height = 12.dp)

                RoundedCoveringButton(
                    onButtonClick = {
                        /*TODO*/
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.setting_cache_clear),
                        style = MaterialTheme.typography.labelLarge
                    )
                }

            }
        }
    }
}
@Stable
@Composable
fun receiveThemeListValues(): List<String> {
    return ThemeSetting.all().map {
        stringResource(id = it.nameId)
    }
}

