package uiwithlogic.inside

import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import org.real.AppDatabase
import uiwithlogic.inside.attendance.attendanceRoute
import uiwithlogic.inside.home.homeRoute
import uiwithlogic.inside.profile.accountRoute
import uiwithlogic.inside.profile.ui.AccountViewModel
import uiwithlogic.inside.settings.settingRoute
import uiwithlogic.inside.settings.ui.SettingsViewModel
import uiwithlogic.nav.Attendance
import uiwithlogic.nav.NavDestination
import utils.ScreenType

fun RouteBuilder.insideRoute(
    group: String,
    appDatabase: AppDatabase,
    screenType: ScreenType,
    navigator: Navigator,
    navDestination: NavDestination,
    accountViewModel: AccountViewModel,
    settingsViewModel: SettingsViewModel,
    notLoggedIn: () -> Unit,
) {
    group(route = group, initialRoute = Attendance.Home.name) {
        accountRoute(
            group = Attendance.Account.name,
            appDatabase = appDatabase,
            accountViewModel = accountViewModel,
            screenType = screenType,
            notLoggedIn = {
                notLoggedIn()
                navigator.navigate(navDestination.name)
            },
        )
        homeRoute(group = Attendance.Home.name)
        settingRoute(group = Attendance.Settings.name, settingsViewModel = settingsViewModel)
        attendanceRoute(group = Attendance.Real.name)
    }
}