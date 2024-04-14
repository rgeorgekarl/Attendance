package uiwithlogic.inside.profile

import org.real.AccountDetails
import uiwithlogic.inside.profile.model.AccountDetailSer

fun AccountDetails.toAccountDetailSer(): AccountDetailSer {
  return AccountDetailSer(
      id = id,
      givenId = givenId ?: 0L,
      firstName = firstName ?: "",
      middleName = middleName ?: "",
      lastName = lastName ?: "",
      nickName = nickName ?: "",
      bio = bio ?: "",
      birth = birth ?: "",
      age = age?.toInt() ?: 0,
      school = school ?: "",
      schoolYear = schoolYear?.toInt() ?: 0,
      course = course ?: "",
      contactNumber = contactNumber?.toInt() ?: 0,
      address = address ?: "",
      email = email ?: "",
      icon = icon ?: ""
  )
}