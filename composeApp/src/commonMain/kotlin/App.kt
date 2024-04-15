import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import theme.AttendanceTheme
import uiwithlogic.AttendanceScreen

@Composable
fun App(driverFactory: DriverFactory, windowWidthSizeClass: WindowWidthSizeClass) {
    val database = createDatabase(driverFactory)
    AttendanceTheme(database) {
        PreComposeApp {
            AttendanceScreen(
                database,
                windowWidthSizeClass,

                )
        }
    }
}