package uiwithlogic.inside.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.viewmodel.viewModel
import org.real.AppDatabase
import uiwithlogic.commonUiUtils.LoadingScreen
import uiwithlogic.inside.profile.repo.AccountRepoImp
import uiwithlogic.model.UserState
import utils.ScreenType

@Composable
internal fun AccountScreen(
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
    var saveOpen by rememberSaveable { mutableStateOf(false) }
    var cancelOpen by rememberSaveable { mutableStateOf(false) }
    Box {
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
                    editable = accountUiState.editable,
                    isEditable = {
                        accountViewModel.changeEditState(false)
                    },
                    saveOnClick = { saveOpen = true },
                    cancelOnClick = { cancelOpen = true },
                    screenType = screenType,
                    modifier = modifier,
                )
                if (saveOpen) {
                    YesOrNoDialog(
                        onDismissRequest = { saveOpen = false },
                        title = "Save Changes",
                        text = "Are you sure you want to save changes?",
                        confirmButton = {
                            accountViewModel.saveChanges()
                            saveOpen = false
                            accountViewModel.changeEditState(true)
                            accountViewModel.changeUserState(UserState.Loading)
                        },
                        dismissButton = { saveOpen = false },
                        modifier = Modifier
                    )
                } else {
                    if (cancelOpen) {
                        YesOrNoDialog(
                            onDismissRequest = { cancelOpen = false },
                            title = "Cancel Changes",
                            text = "Are you sure you want to cancel changes?",
                            confirmButton = {
                                cancelOpen = false
                                accountViewModel.changeEditState(true)
                                accountViewModel.changeUserState(UserState.Loading)

                            },
                            dismissButton = { cancelOpen = false },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun YesOrNoDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    confirmButton: () -> Unit,
    dismissButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            Button(onClick = confirmButton) {
                Text(text = "Yes")
            }
        },
        dismissButton = {
            Button(onClick = dismissButton) {
                Text(text = "No")
            }
        },
        modifier = modifier
    )
}