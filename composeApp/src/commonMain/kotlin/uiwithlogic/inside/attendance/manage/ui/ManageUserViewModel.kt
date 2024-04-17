package uiwithlogic.inside.attendance.manage.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import uiwithlogic.inside.attendance.common.model.Room
import uiwithlogic.inside.attendance.manage.model.ManageUiState
import uiwithlogic.inside.attendance.manage.repo.ManageRepo

class ManageUserViewModel(
    private val manageRepo: ManageRepo
): ViewModel() {
    private val _manageUiState = MutableStateFlow(ManageUiState())
    val manageUiState = _manageUiState.asStateFlow()

    fun addRoom() {
        viewModelScope.launch {
            manageRepo.upsertRoom(
                Room(
                name = _manageUiState.value.roomName,
                description = _manageUiState.value.roomDescription,
                roomNumber = _manageUiState.value.roomNumber
                )
            )
        }
    }

    fun getRooms() {
        viewModelScope.launch {
            val rooms = manageRepo.getRooms()
            updateRooms(rooms)
        }
    }

    fun changeCurrentRoom(room: Room) {
        _manageUiState.value = _manageUiState.value.copy(currentRoom = room)
    }

    fun updateRoomName(roomName: String) {
        _manageUiState.value = _manageUiState.value.copy(roomName = roomName)
    }

    fun updateRoomDescription(roomDescription: String) {
        _manageUiState.value = _manageUiState.value.copy(roomDescription = roomDescription)
    }

    fun updateRoomNumber(roomNumber: String) {
        _manageUiState.value = _manageUiState.value.copy(roomNumber = roomNumber)
    }

    private fun updateRooms(rooms: List<Room>) {
        _manageUiState.value = _manageUiState.value.copy(rooms = rooms)
    }

    fun clearInput() {
        _manageUiState.value = _manageUiState.value.copy(
            roomName = "",
            roomDescription = "",
            roomNumber = "",
            )
    }

}