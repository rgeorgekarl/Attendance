package uiwithlogic.inside.attendance.manage.repo

import datasource.Supabase.client
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import org.real.AppDatabase
import uiwithlogic.inside.attendance.common.model.Room
import uiwithlogic.inside.profile.model.AccountDetailSer

interface ManageRepo {
    suspend fun upsertRoom(room: Room)
    suspend fun deleteRoom(room: Room)
    suspend fun getRooms(): List<Room>
}

class ManageRepoImpl(
    appDatabase: AppDatabase
): ManageRepo {
    private val queries = appDatabase.roomQueries
    private val queries2 = appDatabase.accountDetailsQueries

    override suspend fun upsertRoom(room: Room) {
        client.from("room").upsert(room.copy(createdBy = client.auth.currentUserOrNull()?.email.toString()))
    }

    override suspend fun deleteRoom(room: Room) {
        client.from("room").delete{ filter { Room::roomId eq room.roomId }}
    }

    override suspend fun getRooms(): List<Room> {
        return client.from("room").select{
            filter {
                 eq("createdBy", client.auth.currentUserOrNull()?.email.toString())
            }
        }.decodeList<Room>()
    }
}