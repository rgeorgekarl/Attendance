package uiwithlogic.inside.attendance.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import utils.ScreenType

@Composable
fun BottomRealBar(
    text: String,
    chosen: String,
    onRefresh: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    screenType: ScreenType = ScreenType.COMPACT,
    modifier: Modifier = Modifier
) {
    val floatingDpSize: Dp
    val title: TextStyle
    val chosenTextStyle: TextStyle
    when (screenType ) {
        ScreenType.COMPACT -> {
            floatingDpSize = 36.dp
            title = MaterialTheme.typography.titleMedium
            chosenTextStyle = MaterialTheme.typography.bodyMedium
        }
        ScreenType.MEDIUM -> {
            floatingDpSize = 48.dp
            title = MaterialTheme.typography.titleLarge
            chosenTextStyle = MaterialTheme.typography.bodyLarge
        }
        ScreenType.EXPANDED -> {
            floatingDpSize = 56.dp
            title = MaterialTheme.typography.titleLarge
            chosenTextStyle = MaterialTheme.typography.bodyLarge
        }
    }
    BottomAppBar(
        actions = {
            Text(
                text = "$text: ",
                style = title,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = chosen,
                style = chosenTextStyle,
                )

        },
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FloatingActionButton(
                    onClick = { onRefresh() },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    modifier = Modifier.size(floatingDpSize).align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.Refresh, "Localized description")
                }
                FloatingActionButton(
                    onClick = { onDeleteClick() },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    modifier = Modifier.size(floatingDpSize).align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.Delete, "Localized description")
                }
                FloatingActionButton(
                    onClick = { onEditClick() },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    modifier = Modifier.size(floatingDpSize).align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.Edit, "Localized description")
                }
                FloatingActionButton(
                    onClick = {onAddClick() },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    modifier = Modifier.size(floatingDpSize).align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.Add, "Localized description")
                }
                FloatingActionButton(
                    onClick = { onNextClick() },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                    modifier = Modifier.size(floatingDpSize).align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Filled.ArrowForwardIos, "Localized description")
                }
            }
        }
    )
}