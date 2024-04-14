import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.viewmodel.viewModel
import theme.AttendanceTheme
import theme.LocalDark
import theme.ThemeColor
import uiwithlogic.AttendanceScreen

@Composable
fun App(driverFactory: DriverFactory, windowWidthSizeClass: WindowWidthSizeClass) {
    val database = createDatabase(driverFactory)
    val theme = database.settingsQueries.getSettings().executeAsOne()
    val themeColor = ThemeColor.current
    val darkState = LocalDark.current
    LaunchedEffect(Unit) {
        themeColor.value = theme.theme?.toInt()!!
        darkState.value = theme.isDarkMode == 1L
    }
    AttendanceTheme {
        PreComposeApp {
            AttendanceScreen(
                database,
                windowWidthSizeClass,

                )
        }
    }
}