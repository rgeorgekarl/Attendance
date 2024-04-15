package uiwithlogic.inside.attendance.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uiwithlogic.inside.attendance.common.model.Room

@Composable
fun RoomListScreen(
    roomList: List<Room>,
    modifier: Modifier = Modifier
) {
    if (roomList.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = "No room exist"
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
        ) {
            items(roomList.size, key = { it -> roomList[it].roomId }) { index ->
                RoomItemCard(
                    roomName = roomList[index].name,
                    roomDescription = roomList[index].description,
                    roomNumber = roomList[index].roomNumber,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun RoomItemCard(
    roomName: String,
    roomDescription: String,
    roomNumber: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier.weight(1f)) {
                Text(
                    text = roomName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = roomDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                text = roomNumber,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End
            )
        }
    }
}