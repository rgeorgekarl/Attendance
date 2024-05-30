package uiwithlogic.nav.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

//engstudent2
//abc@123

//CKcomLab0010
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopBar(
    title: String,
    navImageVector: ImageVector,
    navOnClick: () -> Unit,
    showNav: Boolean = true,
    actImageVector: ImageVector,
    logoutOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
                NavIcon(
                    imageVector = navImageVector,
                    contentDescription = "nav",
                    onClick = navOnClick,
                    modifier = Modifier.padding(16.dp)
                )
        },
        actions = {
            NavIcon(
                imageVector = actImageVector,
                contentDescription = "Account",
                onClick = {
                    isContextMenuVisible = true
                },
                modifier = Modifier.padding(16.dp)
                    .onSizeChanged {
                        itemHeight = with(density) { it.height.toDp() }
                    }
            )
            DropdownMenu(
                expanded = isContextMenuVisible,
                onDismissRequest = { isContextMenuVisible = false },

                ) {
                DropdownMenuItem(
                    onClick = {
                        logoutOnClick()
                    },
                    text = {
                        Text(text = "Logout")
                    }
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier
    )
}