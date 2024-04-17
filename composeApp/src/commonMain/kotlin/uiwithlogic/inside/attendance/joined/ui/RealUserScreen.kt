package uiwithlogic.inside.attendance.joined.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import uiwithlogic.inside.attendance.common.ui.RoomListScreen
import uiwithlogic.inside.attendance.common.model.Room

@Composable
fun RealUserScreen() {
    Column {
        RoomListScreen(
            modifier = Modifier.fillMaxSize().weight(1f),
            roomList = listOf(
                Room(
                    roomId = 1,
                    name = "Room 1",
                    description = "Room 1 Description",
                    roomNumber = "2",
                    createdBy = "User 1",
                ),
            )
        )
        BottomNavigationSample()
    }
}

@Composable
fun BottomNavigationSample() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    BottomAppBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}
