import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import theme.AttendanceTheme
import uiwithlogic.AttendanceScreen
import uiwithlogic.inside.attendance.manage.ui.RealManageScreen

@Composable
fun App(driverFactory: DriverFactory, windowWidthSizeClass: WindowWidthSizeClass) {
    val database = createDatabase(driverFactory)
    AttendanceTheme(database) {
        PreComposeApp {
            AttendanceScreen(
                appDatabase = database,
                windowSize = windowWidthSizeClass,
            )
        }
    }
}