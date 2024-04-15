package uiwithlogic.auth

import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.RouteBuilder
import uiwithlogic.auth.ui.AuthScreen
import uiwithlogic.auth.ui.AuthViewModel
import utils.ScreenType


internal fun RouteBuilder.authRoute(
    group: String,
    viewModel: AuthViewModel,
    screenType: ScreenType,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    group(route = group,initialRoute = AuthDestination.AuthScreen.name) {
        scene(AuthDestination.AuthScreen.name) {
            AuthScreen(
                viewModel = viewModel,
                screenType = screenType,
                onSuccess = onSuccess,
                modifier = modifier
            )
        }
    }
}