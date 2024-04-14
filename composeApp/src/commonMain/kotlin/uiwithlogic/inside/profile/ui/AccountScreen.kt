package uiwithlogic.inside.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.*
import moe.tlaster.precompose.viewmodel.viewModel
import org.real.AppDatabase
import uiwithlogic.inside.profile.repo.AccountRepoImp
import uiwithlogic.commonUiUtils.LoadingScreen
import uiwithlogic.model.UserState
import utils.ScreenType

@Composable
fun AccountScreen(
    appDatabase: AppDatabase,
    accountViewModel: AccountViewModel = viewModel(modelClass = AccountViewModel::class) {
        AccountViewModel(
            AccountRepoImp(
                database = appDatabase
            ),
        )
                                                                                         },
    screenType: ScreenType,
    notLoggedIn: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val userState by accountViewModel.accountState.collectAsState()
    val accountUiState by accountViewModel.inputState.collectAsState()

    when (userState) {
        UserState.NotLoggedIn -> {
            notLoggedIn()
        }

        is UserState.Error -> {
            // Show error screen
        }

        is UserState.Success -> {
            // Show success screen
        }

        is UserState.Loading -> {
            // Show loading screen
            LaunchedEffect(Unit) {
                accountViewModel.fetchAccount()
                accountViewModel.changeUserState(UserState.LoggedIn)
            }
            LoadingScreen(modifier = modifier)

        }

        is UserState.LoggedIn -> {
            // Show logged in screen
            AccountDetailsScreen(
                firstName = accountUiState.firstName,
                onFirstNameChange = { accountViewModel.updateFirstName(it) },
                middleName = accountUiState.middleName,
                onMiddleNameChange = { accountViewModel.updateMiddleName(it) },
                lastName = accountUiState.lastName,
                onLastNameChange = { accountViewModel.updateLastName(it) },
                nickName = accountUiState.nickName,
                onNickNameChange = { accountViewModel.updateNickName(it) },
                bio = accountUiState.bio,
                onBioChange = { accountViewModel.updateBio(it) },
                birth = accountUiState.birth,
                onBirthChange = {
                    accountViewModel.updateBirth(it)
                },
                age = accountUiState.age,
                school = accountUiState.school,
                onSchoolChange = { accountViewModel.updateSchool(it) },
                schoolYear = accountUiState.schoolYear,
                onSchoolYearChange = { accountViewModel.updateSchoolYear(it) },
                course = accountUiState.course,
                onCourseChange = { accountViewModel.updateCourse(it) },
                contactNumber = accountUiState.contactNumber,
                onContactNumberChange = { accountViewModel.updateContactNumber(it) },
                address = accountUiState.address,
                onAddressChange = { accountViewModel.updateAddress(it) },
                email = accountUiState.email,
                onEmailChange = { accountViewModel.updateEmail(it) },
                screenType = screenType,
                logout = {accountViewModel.signOut()},
                modifier = modifier,
            )
        }
    }
}