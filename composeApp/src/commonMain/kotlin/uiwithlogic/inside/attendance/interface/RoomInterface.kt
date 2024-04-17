package uiwithlogic.inside.attendance.`interface`

import uiwithlogic.inside.attendance.common.model.Room

interface RoomInterface {
    suspend fun getRooms(id: String): List<Room>
    suspend fun upsertRoom(room: Room)
}