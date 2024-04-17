package uiwithlogic.inside.attendance

import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import org.real.AppDatabase
import uiwithlogic.inside.attendance.manage.ui.RealManageScreen
import utils.ScreenType

fun RouteBuilder.attendanceRoute(
    group: String,
    appDatabase: AppDatabase,
    screenType: ScreenType,
    navigator: Navigator
) {
    group(route = group, initialRoute = RealDestination.AttendanceScreen.name) {
        scene(RealDestination.AttendanceScreen.name){
            RealScreen(
                userOnClick = {navigator.navigate(RealDestination.RealUserScreen.name)},
                manageOnClick = {navigator.navigate(RealDestination.ManageScreen.name)}
            )
        }
        scene(RealDestination.RealUserScreen.name){

        }
        scene(RealDestination.ManageScreen.name){
            RealManageScreen(
                appDatabase = appDatabase,
                screenType = screenType
            )
        }
    }
}