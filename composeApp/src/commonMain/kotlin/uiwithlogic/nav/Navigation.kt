package uiwithlogic.nav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import org.real.AppDatabase
import uiwithlogic.nav.model.NavigationState
import uiwithlogic.nav.util.CenterTopBar
import uiwithlogic.nav.util.DismissibleNavDrawer
import uiwithlogic.nav.util.NavDrawerContent
import uiwithlogic.nav.util.list
import utils.ScreenType

@Composable
fun Nav(
    appDatabase: AppDatabase,
    screenType: ScreenType,
    navigationViewModel: NavigationViewModel = viewModel(NavigationViewModel::class) { NavigationViewModel() },
) {
    val navigator = rememberNavigator()
    val navState by navigationViewModel.destinationState.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    when (screenType) {
        ScreenType.EXPANDED  -> {
            if (navState.destination != NavDestination.Auth) {
                PermanentNavigationDrawer(
                    drawerContent = {
                        PermanentDrawerSheet {
                            NavDrawerContent(
                                selectedDestination = navState.attendance,
                                onTabPressed = { navigationViewModel.updateAttendance(it) },
                                navigationItemContentList = list,
                                modifier = Modifier
                            )
                        }
                    }
                ) {
                    AttendanceScaffold(
                        appDatabase,
                        screenType,
                        navigator,
                        navigationViewModel,
                        navState,
                        navOnClick = { scope.launch { drawerState.open() } },
                        actOnClick = { navigationViewModel.updateDestination(NavDestination.Auth) }
                    )
                }
            } else {
                AttendanceScaffold(
                    appDatabase,
                    screenType,
                    navigator,
                    navigationViewModel,
                    navState,
                    navOnClick = { scope.launch { drawerState.open() } },
                    actOnClick = { navigationViewModel.updateDestination(NavDestination.Auth) }
                )
            }
        }
        else -> {
            DismissibleNavDrawer(
                navDrawerCon = {
                    NavDrawerContent(
                        selectedDestination = navState.attendance,
                        onTabPressed = {
                            runBlocking {
                                navigationViewModel.updateAttendance(it)
                                navigator.navigate(it.name)
                            }
                                       },
                        navigationItemContentList = list,
                        modifier = Modifier
                    )
                },
                drawerState = drawerState,
                content = {
                    AttendanceScaffold(
                        appDatabase,
                        screenType,
                        navigator,
                        navigationViewModel,
                        navState,
                        navOnClick = { scope.launch { drawerState.open() } },
                        actOnClick = { navigationViewModel.updateDestination(NavDestination.Auth) }
                    )
                }
            )
        }
    }
}

@Composable
fun AttendanceScaffold(
    appDatabase: AppDatabase,
    screenType: ScreenType,
    navigator: Navigator,
    navigationViewModel: NavigationViewModel = viewModel(NavigationViewModel::class) { NavigationViewModel() },
    navState: NavigationState,
    navOnClick: () -> Unit,
    actOnClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            AnimatedVisibility(visible = navState.destination != NavDestination.Auth) {
                CenterTopBar(
                    title = navState.attendance.name,
                    navImageVector = Icons.Filled.Menu,
                    navOnClick = navOnClick,
                    actImageVector = Icons.Filled.AccountBox,
                    actOnClick = actOnClick,
                    modifier = Modifier
                )
            }
        },
        ) {
        AttendanceNav(
            appDatabase,
            screenType,
            navigator,
            authSuccess = { navigationViewModel.updateDestination(NavDestination.Attendance) },
            notLoggedIn = { navigationViewModel.updateDestination(NavDestination.Auth) },
            navDestination = navState.destination,
        )
    }
}

