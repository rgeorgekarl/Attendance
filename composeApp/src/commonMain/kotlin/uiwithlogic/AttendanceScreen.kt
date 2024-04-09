package uiwithlogic

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import org.real.AppDatabase
import uiwithlogic.nav.AttendanceNav
import utils.ScreenType

@Composable
fun AttendanceScreen(
    appDatabase: AppDatabase,
    windowSize: WindowWidthSizeClass
) {
    val screenType: ScreenType = when (windowSize) {
        WindowWidthSizeClass.Compact -> ScreenType.COMPACT
        WindowWidthSizeClass.Medium -> ScreenType.MEDIUM
        WindowWidthSizeClass.Expanded -> ScreenType.EXPANDED
        else -> ScreenType.COMPACT

    }
    AttendanceNav(
        appDatabase = appDatabase,
        screenType = screenType,
    )
}