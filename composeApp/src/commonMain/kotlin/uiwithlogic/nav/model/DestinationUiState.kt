package uiwithlogic.nav.model

import uiwithlogic.nav.Attendance
import uiwithlogic.nav.NavDestination

data class NavigationState(
    val destination: NavDestination = NavDestination.Auth,
    val attendance: Attendance = Attendance.Home,
    val isDrawerOpen: Boolean = false,
)
