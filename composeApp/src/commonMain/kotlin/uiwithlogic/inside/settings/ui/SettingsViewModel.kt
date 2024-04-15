package uiwithlogic.inside.settings.ui

import moe.tlaster.precompose.viewmodel.ViewModel
import org.real.AppDatabase

class SettingsViewModel(
    appDatabase: AppDatabase
) : ViewModel() {
    private val settingsQueries = appDatabase.settingsQueries


    fun insertOrReplace(theme: Long, isDarkMode: Long) {
        settingsQueries.insertOrReplace(theme, isDarkMode)

    }

}

