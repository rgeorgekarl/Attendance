package uiwithlogic.inside.attendance.joined.repo

import datasource.Supabase.client
import io.github.jan.supabase.postgrest.from
import uiwithlogic.inside.attendance.common.model.Room
import uiwithlogic.inside.attendance.`interface`.RoomInterface


class UserRepoImp(): RoomInterface {
    override suspend fun getRooms(id: String): List<Room> {
        return client.from("room").select().decodeList()
    }

    override suspend fun upsertRoom(room: Room) {
        client.from("room").upsert(room)
    }
}
