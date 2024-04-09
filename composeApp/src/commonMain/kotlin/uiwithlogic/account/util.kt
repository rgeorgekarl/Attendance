package uiwithlogic.account

import org.real.AccountDetails
import uiwithlogic.account.model.AccountDetailSer
import uiwithlogic.account.model.AccountUiState

fun toAccountDetails(
    id: String,
    givenId: Int,
    firstName: String,
    middleName: String,
    lastName: String,
    nickName: String,
    bio: String,
    birth: String,
    age: String,
    school: String,
    schoolYear: String,
    course: String,
    contactNumber: String,
    address: String,
    email: String,
    icon: String,
): AccountDetails {
    return AccountDetails(
        id = id,
        givenId = givenId.toLong(),
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        nickName = nickName,
        bio = bio,
        birth = birth,
        age = age.toLong(),
        school = school,
        schoolYear = schoolYear.toLong(),
        course = course,
        contactNumber = contactNumber.toLong(),
        address = address,
        email = email,
        icon = icon,
    )
}

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