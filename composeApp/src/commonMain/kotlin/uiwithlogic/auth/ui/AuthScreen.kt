package uiwithlogic.auth.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Rotate90DegreesCw
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModel
import org.real.AppDatabase
import uiwithlogic.auth.model.AuthType
import uiwithlogic.commonUiUtils.LoadingScreen
import uiwithlogic.model.UserState
import uiwithlogic.util.getToken
import utils.ScreenType

@Composable
fun AuthScreen(
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
                LaunchedEffect(Unit) {
                    launch {viewModel.checkUser()}
                    delay(3000L)

                }
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
                                viewModel.clearInput()
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
fun ErrorScreen(
    message: String,
    tryAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium
        )
        Button(onClick = { tryAgain() }) {
            Text(text = "Try again")
        }
    }
}