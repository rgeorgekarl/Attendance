package uiwithlogic.inside.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.RouteBuilder
import org.real.AppDatabase
import uiwithlogic.inside.profile.ui.AccountScreen
import uiwithlogic.inside.profile.ui.AccountViewModel
import utils.ScreenType


fun RouteBuilder.accountRoute(
    group: String,
    appDatabase: AppDatabase,
    accountViewModel: AccountViewModel,
    screenType: ScreenType,
    notLoggedIn: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    group(route = group, initialRoute = AccountDestination.AccountScreen.name) {
        scene(AccountDestination.AccountScreen.name) {
            AccountScreen(
                appDatabase = appDatabase,
                accountViewModel = accountViewModel,
                screenType = screenType,
                notLoggedIn = notLoggedIn,
                modifier = modifier.padding(top = 56.dp)
            )
        }
    }
}

