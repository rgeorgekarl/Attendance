package uiwithlogic.account

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import org.real.AppDatabase
import uiwithlogic.account.ui.AccountScreen
import uiwithlogic.account.ui.AccountViewModel
import utils.ScreenType


fun RouteBuilder.accountRoute(
    group: String,
    appDatabase: AppDatabase,
    accountViewModel: AccountViewModel,
    screenType: ScreenType,
    notLoggedIn: () -> Unit = {},
) {
    group(route = group, initialRoute = AccountDestination.AccountScreen.name) {
        scene(AccountDestination.AccountScreen.name) {
            AccountScreen(
                appDatabase = appDatabase,
                accountViewModel = accountViewModel,
                screenType = screenType,
                notLoggedIn = notLoggedIn
            )
        }
    }

}