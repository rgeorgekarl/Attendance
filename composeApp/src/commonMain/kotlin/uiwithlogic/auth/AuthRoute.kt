package uiwithlogic.auth

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.route.Route
import uiwithlogic.auth.ui.AuthScreen
import uiwithlogic.auth.ui.AuthViewModel
import utils.ScreenType


fun RouteBuilder.authRoute(
    group: String,
    viewModel: AuthViewModel,
    screenType: ScreenType,
    onSuccess: () -> Unit,
) {
    group(route = group,initialRoute = AuthDestination.AuthScreen.name) {
        scene(AuthDestination.AuthScreen.name) {
            AuthScreen(
                viewModel = viewModel,
                screenType = screenType,
                onSuccess = onSuccess,
            )
        }
    }
}