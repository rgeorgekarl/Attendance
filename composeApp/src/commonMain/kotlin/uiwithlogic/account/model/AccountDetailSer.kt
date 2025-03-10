package uiwithlogic.account.model

import kotlinx.datetime.DateTimePeriod
import kotlinx.serialization.Serializable

@Serializable
data class AccountDetailSer(
    val id: String = "",
    val givenId: Long? = 0L,
    val firstName: String = "",
    val middleName: String = "",
    val lastName: String = "",
    val nickName: String = "",
    val bio: String = "",
    val birth: String? = "",
    val age: Int? = 0,
    val school: String = "",
    val schoolYear: Int? = 0,
    val course: String = "",
    val contactNumber: Int? = 0,
    val address: String = "",
    val email: String = "",
    val icon: String = "",
)
