package uiwithlogic.inside.attendance

import moe.tlaster.precompose.navigation.RouteBuilder
import uiwithlogic.inside.attendance.ui.AttendanceScreen

fun RouteBuilder.attendanceRoute(
    group: String,
) {
    group(route = group, initialRoute = AttendanceDestination.AttendanceScreen.name) {
        scene(AttendanceDestination.AttendanceScreen.name){
            AttendanceScreen()
        }
    }
}