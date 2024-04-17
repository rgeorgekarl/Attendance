package uiwithlogic.inside.attendance.common.model

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val roomId: Int = 1,
    val name: String = "",
    val description: String = "",
    val roomNumber: String = "",
    val createdBy: String = "",
)
