package uiwithlogic.nav.util

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavDrawer(
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    ModalNavigationDrawer(
        drawerContent = drawerContent,
        drawerState = drawerState,
        content = content,
        modifier = modifier
    )
}