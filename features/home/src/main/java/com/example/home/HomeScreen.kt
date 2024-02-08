package com.example.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.MainDivider
import com.example.domain.models.TaskModel
import com.example.domain.models.TaskPin
import com.example.home.components.TaskItem
import com.example.home.viewmodels.MainViewModel
import com.example.ui.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState().value

    val filterItems = rememberFilterItems()
    
    val tasks = listOf(
        TaskModel(
            title = "Убраться в квартире и решить задачи",
            taskPin = listOf(
                TaskPin(
                    name = "важное",
                    containerColor = MaterialTheme.colorScheme.secondary,
                    textColor = MaterialTheme.colorScheme.onSecondary
                ),
                TaskPin(
                    name = "домашнее",
                    containerColor = Color(0xFFFCFF82),
                    textColor = MaterialTheme.colorScheme.onSecondary
                ),
                TaskPin(
                    name = "уборка",
                    containerColor = Color(0xFFFF8ADE),
                    textColor = MaterialTheme.colorScheme.onSecondary
                ),
            ),
            content = "Убираемся в квартире, пылесосим, вытираем пыль, моем посуду и после этого послушать музыку"
        ),
        TaskModel(title = "Убраться в квартире и решить задачи"),
        TaskModel(title = "Убраться в квартире и решить задачи"),
        TaskModel(title = "Убраться в квартире и решить задачи"),
        TaskModel(title = "Убраться в квартире и решить задачи")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp)
    ) {
        Column(
           modifier = modifier
               .fillMaxWidth()
        ){
            Text(
                text = stringResource(id = R.string.daily_planner),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        MainDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 13.dp, bottom = 32.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = filterItems.first()),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    /*TODO replace with correct icon*/
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(id = R.string.filters),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(26.dp)
        ) {
            items(tasks) {
                TaskItem(
                    model = it,
                    onDoneClick = {

                    }
                )
            }
        }
    }
}

@Stable
@Composable
fun rememberFilterItems(): List<Int> {
    return rememberSaveable {
        listOf(
            R.string.filter_importance
        )
    }
}