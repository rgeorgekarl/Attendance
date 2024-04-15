package uiwithlogic.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uiwithlogic.auth.model.AuthType
import uiwithlogic.commonUiUtils.LoadingScreen
import uiwithlogic.model.UserState
import utils.ScreenType

@Composable
internal fun AuthScreen(
    viewModel: AuthViewModel,
    screenType: ScreenType,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val userState by viewModel.userState.collectAsState()
    val inputState by viewModel.inputState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        when (userState) {
            UserState.Loading -> {
                viewModel.checkUser()
                LoadingScreen()
            }

            is UserState.Error -> ErrorScreen(
                message = (userState as UserState.Error).message,
                tryAgain = {
                    viewModel.changeUserState(UserState.NotLoggedIn)
                }
            )

            is UserState.Success -> {
                // create 3 seconds delay before moving to the next screen
                viewModel.changeUserState(UserState.LoggedIn)
            }

            UserState.LoggedIn -> {
                onSuccess()
            }

            is UserState.NotLoggedIn -> {
                AuthContentScreen(
                    auth = inputState.authType,
                    screenType = screenType,
                    email = inputState.email,
                    onEmailChange = { viewModel.onEmailChange(it) },
                    password = inputState.password,
                    onPasswordChange = { viewModel.onPasswordChange(it) },
                    authButton = {
                        when (inputState.authType) {
                            AuthType.LOGIN -> {
                                viewModel.signIn()
                            }

                            AuthType.SIGNUP -> {
                                viewModel.signUp()
                            }
                        }
                    },
                    onAuthTypeChange = { viewModel.onAuthTypeChange() },
                    modifier = Modifier
                )
            }

        }
    }
}


@Composable
private fun ErrorScreen(
    message: String,
    tryAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(onClick = { tryAgain() }) {
            Text(text = "Try again")
        }
    }
}