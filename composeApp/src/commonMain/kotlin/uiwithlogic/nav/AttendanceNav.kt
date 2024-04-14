package uiwithlogic.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import org.real.AppDatabase
import uiwithlogic.inside.profile.accountRoute
import uiwithlogic.inside.profile.repo.AccountRepoImp
import uiwithlogic.inside.profile.ui.AccountViewModel
import uiwithlogic.auth.authRoute
import uiwithlogic.auth.ui.AuthViewModel
import uiwithlogic.inside.home.homeRoute
import uiwithlogic.inside.insideRoute
import uiwithlogic.inside.settings.ui.SettingsViewModel
import utils.ScreenType


/*
* AttendanceNav is a composable function that will be used to navigate to different screens in the app.
* precompose is used to handle the navigation.
 */
@Composable
fun AttendanceNav(
    appDatabase: AppDatabase,
    screenType: ScreenType,
    navigator: Navigator,
    navDestination: NavDestination,
    authSuccess: () -> Unit,
    notLoggedIn: () -> Unit,
    navModifier: Modifier = Modifier,
) {
    Column(
        modifier = navModifier
    ) {
        NavHost(
            navigator = navigator,
            initialRoute = NavDestination.Auth.name,
        ) {
            authRoute(
                group = NavDestination.Auth.name,
                viewModel = AuthViewModel(),
                screenType = screenType,
                onSuccess = {
                    authSuccess()
                    navigator.navigate(navDestination.name)
                },
            )
            insideRoute(
                group = NavDestination.Attendance.name,
                appDatabase = appDatabase,
                screenType = screenType,
                navigator = navigator,
                navDestination = navDestination,
                accountViewModel = AccountViewModel(AccountRepoImp(appDatabase)),
                notLoggedIn = notLoggedIn,
                settingsViewModel = SettingsViewModel(appDatabase),

            )
        }
    }
}