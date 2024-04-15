package uiwithlogic.inside.profile

import org.real.AccountDetails
import uiwithlogic.inside.profile.model.AccountDetailSer
import uiwithlogic.inside.profile.model.AccountUiState

fun AccountUiState.toAccountDetailSer(): AccountDetailSer {
  return AccountDetailSer(
      id = id,
      givenId = givenId,
      firstName = firstName,
      middleName = middleName,
      lastName = lastName,
      nickName = nickName,
      bio = bio,
      birth = birth,
      age = age.toIntOrNull(),
      school = school,
      schoolYear = schoolYear.toIntOrNull(),
      course = course,
      contactNumber = contactNumber.toIntOrNull(),
      address = address,
      email = email,
      icon = icon
  )
}

fun AccountDetails.toAccountDetailSer(): AccountDetailSer {
  return AccountDetailSer(
      id = id,
      givenId = givenId?.toInt() ?: 0,
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