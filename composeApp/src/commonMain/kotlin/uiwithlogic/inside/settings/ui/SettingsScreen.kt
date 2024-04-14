package uiwithlogic.inside.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import moe.tlaster.precompose.viewmodel.viewModel
import theme.LocalDark
import theme.ThemeColor
import uiwithlogic.inside.settings.util.colors

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier,

){
    val settingsState by viewModel.settingsUiState.collectAsState()
    val themeColorState = ThemeColor.current
    val isDarkState = LocalDark.current

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main) {
            themeColorState.value = settingsState.theme
            isDarkState.value = settingsState.isDarkMode
        }
    }
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
                        settingsState.access,
                        themeColorState.value.toLong(),
                        if (isDark) 1L else 0L
                    )
                }
            )
            VerticalGridList(
                colors = colors,
                selected = {
                    onSelectedColor(it)
                    viewModel.insertOrReplace(
                        settingsState.access,
                        it.toLong(),
                        if (isDarkState.value) 1L else 0L
                    )
                }
            )
        }
    }
}

