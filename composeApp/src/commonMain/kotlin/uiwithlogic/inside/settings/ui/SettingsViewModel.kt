package uiwithlogic.inside.settings.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import org.real.AppDatabase

class SettingsViewModel(
    appDatabase: AppDatabase
): ViewModel() {
    private val _settingsUiState = MutableStateFlow(SettingsState())
    var settingsUiState = _settingsUiState.asStateFlow()
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

    fun insertOrReplace(access: String, theme: Long, isDarkMode: Long) {
        settingsQueries.insertOrReplace(access, theme, isDarkMode)
        _settingsUiState.value = _settingsUiState.value.copy(
            access = access,
            theme = theme.toInt(),
            isDarkMode = isDarkMode == 1L
        )
    }

}

data class SettingsState(
    val access: String= "settings",
    val theme: Int = 1,
    val isDarkMode: Boolean = false
)