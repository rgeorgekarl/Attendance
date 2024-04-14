package uiwithlogic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import org.real.AppDatabase

class AttendanceViewModel(
    private val appDatabase: AppDatabase
): ViewModel() {
    private val _settingsUiState = MutableStateFlow(SettingsState())
    val settingsUiState = _settingsUiState.asStateFlow()

    private val settingsQueries = appDatabase.settingsQueries

    init {
        fetchSettings()
    }

    private fun fetchSettings() {
        val settings = settingsQueries.getSettings().executeAsOne()
        _settingsUiState.value = SettingsState(
            access = settings.access ?: "settings",
            theme = settings.theme?.toInt() ?: 1,
            isDarkMode = settings.isDarkMode == 1L
        )
    }

    fun updateTheme(theme: Int) {
        _settingsUiState.value = _settingsUiState.value.copy(theme = theme)
        settingsQueries.insertOrReplace(
            access = "settings",
            theme = theme.toLong(),
            isDarkMode = if (_settingsUiState.value.isDarkMode) 1L else 0L
        )
    }

    fun updateDarkMode(isDarkMode: Boolean) {
        _settingsUiState.value = _settingsUiState.value.copy(isDarkMode = isDarkMode)
        settingsQueries.insertOrReplace(
            access = "settings",
            theme = _settingsUiState.value.theme.toLong(),
            isDarkMode = if (isDarkMode) 1L else 0L
        )
    }



}

data class SettingsState(
    val access: String = "settings",
    val theme: Int = 1,
    val isDarkMode: Boolean = false
)