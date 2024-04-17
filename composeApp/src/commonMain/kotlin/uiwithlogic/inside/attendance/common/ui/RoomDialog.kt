package uiwithlogic.inside.attendance.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import uiwithlogic.commonUiUtils.RealButton


@Composable
fun RoomDialog(
    preTitle: String,
    roomName: String,
    onRoomNameChange: (String) -> Unit,
    roomDescription: String,
    onRoomDescriptionChange: (String) -> Unit,
    roomNumber: String,
    onRoomNumberChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onCancel: () -> Unit,
    yes: () -> Unit,
    yesText: String,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ){
        Card(
         modifier = modifier
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(
                    text = "$preTitle Room",
                    style = MaterialTheme.typography.titleLarge
                )
                RealTextField(
                    value = roomName,
                    onValueChange = onRoomNameChange,
                    label = "Room Name",
                    readOnly = readOnly
                )
                RealTextField(
                    value = roomDescription,
                    onValueChange = onRoomDescriptionChange,
                    label = "Room Description",
                    readOnly = readOnly
                )
                RealTextField(
                    value = roomNumber,
                    onValueChange = onRoomNumberChange,
                    label = "Room Number",
                    readOnly = readOnly
                )
                Row {
                    RealButton(
                        title = "Cancel",
                        onClick = onCancel,
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                        modifier = Modifier.padding(8.dp)
                    )
                    RealButton(
                        title = yesText,
                        onClick = yes,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}