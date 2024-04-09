package uiwithlogic.model

sealed class UserState {
    object LoggedIn : UserState()
    object NotLoggedIn : UserState()
    object Loading : UserState()
    data class Success(val message: String) : UserState()
    data class Error(val message: String) : UserState()
}