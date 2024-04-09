import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.window.ComposeUIViewController

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun MainViewController() = ComposeUIViewController {
    val windowWidthSizeClass = calculateWindowSizeClass()
    val driverFactory = DriverFactory()
    App(
        windowWidthSizeClass = windowWidthSizeClass.widthSizeClass,
        driverFactory = driverFactory
    )
}