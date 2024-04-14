package uiwithlogic.nav.util

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DismissibleNavDrawer(
    navDrawerCon: @Composable () -> Unit,
    content: @Composable () -> Unit,
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(240.dp)) {
                navDrawerCon()
            }

        },
        content = content,
        modifier = modifier
    )
}