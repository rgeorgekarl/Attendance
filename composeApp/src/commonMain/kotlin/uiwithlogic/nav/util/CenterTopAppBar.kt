package uiwithlogic.nav.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
    actImageVector: ImageVector,
    actOnClick: () -> Unit,
    modifier: Modifier= Modifier

) {
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
                onClick = actOnClick,
                modifier = Modifier.padding(16.dp)
            )
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