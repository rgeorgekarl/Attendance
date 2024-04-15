package uiwithlogic.nav

import datasource.Supabase.client
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import uiwithlogic.nav.model.NavigationState

class NavigationViewModel : ViewModel() {
    private val _destinationState = MutableStateFlow(NavigationState())
    val destinationState = _destinationState.asStateFlow()

    fun signOut() {
        viewModelScope.launch {
            client.auth.signOut()
            _destinationState.value = _destinationState.value.copy(destination = NavDestination.Auth)
        }
    }

    fun updateDestination(destination: NavDestination) {
        _destinationState.value = _destinationState.value.copy(destination = destination)
    }

    fun updateAttendance(attendance: Attendance) {
        _destinationState.value = _destinationState.value.copy(attendance = attendance)
    }
}