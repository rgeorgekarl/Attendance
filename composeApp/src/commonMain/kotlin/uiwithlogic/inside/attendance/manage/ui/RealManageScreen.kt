package uiwithlogic.inside.attendance.manage.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.viewmodel.viewModel
import org.real.AppDatabase
import uiwithlogic.inside.attendance.common.ui.BottomRealBar
import uiwithlogic.inside.attendance.common.ui.RoomDialog
import uiwithlogic.inside.attendance.common.ui.RoomListScreen
import uiwithlogic.inside.attendance.manage.repo.ManageRepoImpl
import utils.ScreenType


@Composable
fun RealManageScreen(
    appDatabase: AppDatabase,
    viewModel: ManageUserViewModel = viewModel(modelClass = ManageUserViewModel::class) { ManageUserViewModel(
        ManageRepoImpl(appDatabase)
    ) },
    screenType: ScreenType,
    modifier: Modifier = Modifier
) {
    val manageUiState by viewModel.manageUiState.collectAsState()
    var isAddOpen by remember { mutableStateOf(false) }
    var isEditOpen by remember { mutableStateOf(false) }
    var isDeleteOpen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.padding(top = 60.dp)
    ) {
        RoomListScreen(
            click = {viewModel.changeCurrentRoom(it)},
            modifier = Modifier.padding(4.dp).fillMaxSize().weight(1f),
            roomList = manageUiState.rooms
        )
        BottomRealBar(
            text = "Room",
            chosen = manageUiState.currentRoom.name,
            onRefresh = {viewModel.getRooms()},
            onDeleteClick = {
                isDeleteOpen = true
                viewModel.updateRoomName(manageUiState.currentRoom.name)
                viewModel.updateRoomDescription(manageUiState.currentRoom.description)
                viewModel.updateRoomNumber(manageUiState.currentRoom.roomNumber)
                            },
            onEditClick = {
                isEditOpen = true
                viewModel.updateRoomName(manageUiState.currentRoom.name)
                viewModel.updateRoomDescription(manageUiState.currentRoom.description)
                viewModel.updateRoomNumber(manageUiState.currentRoom.roomNumber)
                          },
            onAddClick = {isAddOpen = true},
            onNextClick = {},
            screenType = screenType,
            modifier = Modifier.padding(4.dp)
        )
        if (isAddOpen) {
            RoomDialog(
                preTitle = "Add",
                roomName = manageUiState.roomName,
                onRoomNameChange = {viewModel.updateRoomName(it)},
                roomDescription = manageUiState.roomDescription,
                onRoomDescriptionChange = { viewModel.updateRoomDescription(it)},
                roomNumber = manageUiState.roomNumber,
                onRoomNumberChange = { viewModel.updateRoomNumber(it)},
                onDismissRequest = {
                    isAddOpen = false
                    viewModel.clearInput()
                                   },
                onCancel = {
                    isAddOpen = false
                    viewModel.clearInput()
                           },
                yes = {
                    isAddOpen = false
                    viewModel.addRoom()
                    viewModel.clearInput()
                      },
                yesText = "Add",
                modifier = Modifier.padding(4.dp)
            )
        }

        if (isEditOpen) {
            RoomDialog(
                preTitle = "Edit",
                roomName = manageUiState.roomName,
                onRoomNameChange = {viewModel.updateRoomName(it)},
                roomDescription = manageUiState.roomDescription,
                onRoomDescriptionChange = { viewModel.updateRoomDescription(it)},
                roomNumber = manageUiState.roomNumber,
                onRoomNumberChange = { viewModel.updateRoomNumber(it)},
                onDismissRequest = {
                    isEditOpen = false
                    viewModel.clearInput()
                                   },
                onCancel = {
                    isEditOpen = false
                    viewModel.clearInput()
                           },
                yes = {
                    isEditOpen = false
                    viewModel.clearInput()
                      },
                yesText = "Edit",
                modifier = Modifier.padding(4.dp)
            )
        }
        if (isDeleteOpen) {
            RoomDialog(
                preTitle = "Delete",
                roomName = manageUiState.roomName,
                onRoomNameChange = {viewModel.updateRoomName(it)},
                roomDescription = manageUiState.roomDescription,
                onRoomDescriptionChange = { viewModel.updateRoomDescription(it)},
                roomNumber = manageUiState.roomNumber,
                onRoomNumberChange = { viewModel.updateRoomNumber(it)},
                onDismissRequest = {
                    isDeleteOpen = false
                    viewModel.clearInput()
                                   },
                onCancel = {
                    isDeleteOpen = false
                    viewModel.clearInput()
                           },
                yes = {
                    isDeleteOpen = false
                    viewModel.clearInput()
                      },
                yesText = "Delete",
                readOnly = true,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}