package com.example.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.util.Droppable
import com.example.util.extension.vector

@Composable
fun <T: Droppable> TextDropDown(
    value: T,
    @DrawableRes iconRes: Int,
    dropDownList: List<T>,
    onDropDownClick: (T) -> Unit
) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Column {

        Row (
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .clickable {
                    expanded = expanded.not()
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(id = value.stringId()),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                imageVector = vector(res = iconRes),
                contentDescription = null
            )
        }

        DropdownMenu(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            dropDownList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = it.stringId()),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        onDropDownClick.invoke(it)
                        expanded = false
                    }
                )
            }
        }
    }
}