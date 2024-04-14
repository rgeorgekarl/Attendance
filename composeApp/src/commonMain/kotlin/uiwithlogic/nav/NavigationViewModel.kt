package uiwithlogic.nav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import uiwithlogic.nav.model.NavigationState

class NavigationViewModel: ViewModel() {
    private val _destinationState = MutableStateFlow(NavigationState())
    val destinationState = _destinationState.asStateFlow()



    fun updateDestination(destination: NavDestination) {
        _destinationState.value = _destinationState.value.copy(destination = destination)
    }

    fun updateAttendance(attendance: Attendance) {
        _destinationState.value = _destinationState.value.copy(attendance = attendance)
    }
}