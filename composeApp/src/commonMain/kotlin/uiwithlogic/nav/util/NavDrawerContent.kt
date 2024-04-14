package uiwithlogic.nav.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import uiwithlogic.nav.Attendance
import uiwithlogic.nav.NavDestination
import utils.getResourceImage

data class NavigationItemContent(
    val attendance: Attendance,
    val selected: ImageVector,
    val unselected: ImageVector,
    val text: String
)

val list = listOf(
    NavigationItemContent(
        Attendance.Home,
        selected = Icons.Filled.Home,
        unselected = Icons.Outlined.Home,
        text = "Home"
    ),
    NavigationItemContent(
        Attendance.Real,
        selected = Icons.Filled.Checklist,
        unselected = Icons.Outlined.Checklist,
        text = "Attendance"
    ),
    NavigationItemContent(
        Attendance.Manage,
        selected = Icons.Filled.People,
        unselected = Icons.Outlined.People,
        text = "Profile"
    ),
    NavigationItemContent(
        Attendance.Settings,
        selected = Icons.Filled.Settings,
        unselected = Icons.Outlined.Settings,
        text = "Settings"

    )
)

@Composable
fun NavDrawerContent(
    selectedDestination: Attendance,
    onTabPressed: ((Attendance) -> Unit),
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = getResourceImage("frame_1", "xml"),
            contentDescription = "Logo",
            modifier = Modifier.padding(16.dp)
        )
        for (navItem in navigationItemContentList) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.attendance,
                label = {
                    Text(
                        text = navItem.text,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                },
                icon = {
                    Icon(
                        imageVector = if (selectedDestination == navItem.attendance) navItem.selected else navItem.unselected,
                        contentDescription = navItem.text
                    )
                },
                onClick = {onTabPressed(navItem.attendance)}
            )
        }
    }
}