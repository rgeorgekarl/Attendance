package uiwithlogic.inside.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.LocalDark
import theme.ThemeColor
import uiwithlogic.inside.settings.util.colors

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier,

    ) {
    val themeColorState = ThemeColor.current
    val isDarkState = LocalDark.current

    fun onSelectedColor(color: Int) {
        themeColorState.value = color
    }

    fun onSelectedDarkMode(isDark: Boolean) {
        isDarkState.value = isDark
    }
    Card(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            LightOrDark(
                chosen = { isDark ->
                    onSelectedDarkMode(isDark)
                    viewModel.insertOrReplace(
                        themeColorState.value.toLong(),
                        if (isDark) 1L else 0L
                    )
                }
            )
            HorizontalDivider(
                modifier = Modifier.padding(24.dp),
                thickness = 2.dp
            )
            VerticalGridList(
                colors = colors,
                selected = {
                    onSelectedColor(it)
                    viewModel.insertOrReplace(
                        it.toLong(),
                        if (isDarkState.value) 1L else 0L
                    )
                }
            )
        }
    }
}

