package uiwithlogic.inside.attendance.manage.model

import uiwithlogic.inside.attendance.common.model.Room

data class ManageUiState(
    val rooms: List<Room> = emptyList(),
    val currentRoom: Room = Room(),
    val roomName: String = "",
    val roomDescription: String = "",
    val roomNumber: String = "",

    )
