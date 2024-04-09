package uiwithlogic.nav

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.real.AppDatabase
import uiwithlogic.account.AccountDestination
import uiwithlogic.account.accountRoute
import uiwithlogic.account.repo.AccountRepoImp
import uiwithlogic.account.ui.AccountViewModel
import uiwithlogic.auth.AuthDestination
import uiwithlogic.auth.authRoute
import uiwithlogic.auth.ui.AuthViewModel
import uiwithlogic.commonUiUtils.LoadingScreen
import utils.ScreenType


/*
* AttendanceNav is a composable function that will be used to navigate to different screens in the app.
* precompose is used to handle the navigation.
 */
@Composable
fun AttendanceNav(
    appDatabase: AppDatabase,
    screenType: ScreenType,
) {
    var destination by rememberSaveable { mutableStateOf(NavDestination.Auth) }
    PreComposeApp{
        val navigator = rememberNavigator()
        NavHost(
            navigator = navigator,
            initialRoute = NavDestination.Auth.name,
        ) {
            authRoute(
                group = NavDestination.Auth.name,
                viewModel = AuthViewModel(appDatabase),
                screenType = screenType,
                onSuccess = {
                    destination = NavDestination.Account
                    navigator.navigate(destination.name)
                },
            )
            accountRoute(
                group = NavDestination.Account.name,
                appDatabase = appDatabase,
                accountViewModel = AccountViewModel(AccountRepoImp(appDatabase)),
                screenType = screenType,
                notLoggedIn = {
                    destination = NavDestination.Auth
                    navigator.navigate(destination.name)
                }
            )
        }
    }
}