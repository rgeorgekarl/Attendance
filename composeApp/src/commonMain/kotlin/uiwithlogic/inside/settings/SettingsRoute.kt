package uiwithlogic.inside.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.RouteBuilder
import uiwithlogic.inside.settings.ui.SettingsScreen
import uiwithlogic.inside.settings.ui.SettingsViewModel

fun RouteBuilder.settingRoute(
    group: String,
    settingsViewModel: SettingsViewModel
) {
    group(route = group, initialRoute = SettingsDestination.SettingScreen.name) {
        scene(SettingsDestination.SettingScreen.name){
            Column(
                modifier = Modifier.padding(top = 60.dp)
            ) {
                SettingsScreen(
                    viewModel = settingsViewModel
                )
            }
        }
    }
}