package uiwithlogic.account.ui

import exceptions.YourNotLoggedInException
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.UnknownRestException
import io.ktor.client.network.sockets.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.real.AppDatabase
import uiwithlogic.account.model.AccountUiState
import uiwithlogic.account.repo.AccountRepository
import uiwithlogic.model.UserState
import uiwithlogic.util.getClientSession
import uiwithlogic.util.savePreferences

class AccountViewModel(
    private val accountRepo: AccountRepository,
): ViewModel() {
    private val _accountState = MutableStateFlow<UserState>(UserState.Loading)
    val accountState = _accountState.asStateFlow()
    private val _inputState = MutableStateFlow(AccountUiState())
    val inputState = _inputState.asStateFlow()



    fun fetchAccount(){
        viewModelScope.launch {
            try {
                fetchAccountDetails()
            } catch (e: YourNotLoggedInException) {
                _accountState.value = UserState.NotLoggedIn
                // Handle error
            } catch (e: IOException) {
                fetchAccount()
            } catch (e: HttpRequestException) {
                // Handle error
                fetchAccount()
            } catch (e: UnknownRestException) {
                // Handle error
                fetchAccount()
            } catch (e: SocketTimeoutException) {
                // Handle error
                fetchAccount()
            } catch (e: IOException) {
                // Handle error
                fetchAccount()
            } catch (e: Exception) {
                // Handle error
                _accountState.value = UserState.Error("An error occurred")
                }
        }
    }
    private suspend fun fetchAccountDetails() {
        viewModelScope.launch {
            accountRepo.fetchAccountDetails()
            val accountDetails = accountRepo.getAccountDetails()
            _inputState.value = _inputState.value.copy(
                id = accountDetails.id,
                givenId = accountDetails.givenId?.toInt() ?: 0,
                firstName = accountDetails.firstName,
                middleName = accountDetails.middleName,
                lastName = accountDetails.lastName,
                nickName = accountDetails.nickName,
                bio = accountDetails.bio,
                birth = accountDetails.birth ?:"0",
                age = accountDetails.age.toString(),
                school = accountDetails.school,
                schoolYear = accountDetails.schoolYear.toString(),
                course = accountDetails.course,
                contactNumber = accountDetails.contactNumber.toString(),
                address = accountDetails.address,
                email = accountDetails.email,
                icon = accountDetails.icon,
            )
        }
    }

    fun changeUserState(userState: UserState) {
        _accountState.value = userState
    }
    fun updateFirstName(firstName: String) {
        _inputState.update { currentState ->
            currentState.copy(firstName = firstName)
        }
    }

    fun updateMiddleName(middleName: String) {
        _inputState.value = _inputState.value.copy(middleName = middleName)
    }

    fun updateLastName(lastName: String) {
        _inputState.value = _inputState.value.copy(lastName = lastName)
    }

    fun updateNickName(nickName: String) {
        _inputState.value = _inputState.value.copy(nickName = nickName)
    }

    fun updateBio(bio: String) {
        _inputState.value = _inputState.value.copy(bio = bio)
    }

    fun updateBirth(birth: String) {
        _inputState.value = _inputState.value.copy(birth = birth)
        val current = Clock.System.now().toEpochMilliseconds()
        val birthDate = _inputState.value.birth.toLong()
        val age = (current - birthDate) / 31557600000
        _inputState.value = _inputState.value.copy(age = age.toString())

    }

    fun updateSchool(school: String) {
        _inputState.value = _inputState.value.copy(school = school)
    }

    fun updateSchoolYear(schoolYear: String) {
        _inputState.value = _inputState.value.copy(schoolYear = schoolYear)
    }

    fun updateCourse(course: String) {
        _inputState.value = _inputState.value.copy(course = course)
    }

    fun updateContactNumber(contactNumber: String) {
        _inputState.value = _inputState.value.copy(contactNumber = contactNumber)
    }

    fun updateAddress(address: String) {
        _inputState.value = _inputState.value.copy(address = address)
    }

    fun updateEmail(email: String) {
        _inputState.value = _inputState.value.copy(email = email)
    }
}

