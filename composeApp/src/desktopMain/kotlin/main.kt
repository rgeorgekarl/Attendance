import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() = application {
    val driverFactory = DriverFactory()
    Window(onCloseRequest = ::exitApplication, title = "Attendance") {
        val windowSize = calculateWindowSizeClass()
      App(driverFactory, windowWidthSizeClass = windowSize.widthSizeClass)
    }
}
