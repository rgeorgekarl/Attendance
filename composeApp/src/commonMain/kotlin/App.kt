import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import org.real.AppDatabase
import uiwithlogic.auth.ui.AuthScreen
import uiwithlogic.auth.ui.AuthViewModel
import theme.AttendanceTheme
import uiwithlogic.AttendanceScreen
import uiwithlogic.account.repo.AccountRepoImp
import uiwithlogic.account.ui.AccountDetailsScreen
import uiwithlogic.account.ui.AccountScreen
import uiwithlogic.account.ui.AccountViewModel
import utils.ScreenType

@Composable
fun App(driverFactory: DriverFactory, windowWidthSizeClass: WindowWidthSizeClass) {
    val database = createDatabase(driverFactory)
    AttendanceTheme {
       AttendanceScreen(
           database,
           windowWidthSizeClass
       )
    }
}