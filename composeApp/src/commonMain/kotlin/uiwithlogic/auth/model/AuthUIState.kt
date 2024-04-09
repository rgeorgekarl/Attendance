package uiwithlogic.auth.model

data class AuthUIState(
    val email: String = "",
    val password: String = "",
    val authType: AuthType = AuthType.LOGIN
)

enum class AuthType {
    LOGIN,
    SIGNUP
}