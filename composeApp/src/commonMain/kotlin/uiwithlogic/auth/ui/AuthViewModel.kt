package uiwithlogic.auth.ui

import uiwithlogic.auth.model.AuthType
import uiwithlogic.auth.model.AuthUIState
import datasource.Supabase.client
import exceptions.YourNotLoggedInException
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.UnknownRestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.ktor.client.network.sockets.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope
import uiwithlogic.inside.profile.model.AccountDetailSer
import uiwithlogic.model.UserState
import uiwithlogic.util.getClientSession

class AuthViewModel(): ViewModel() {
    private val _inputState = MutableStateFlow(AuthUIState())
    val inputState = _inputState.asStateFlow()

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState = _userState.asStateFlow()

    fun signUp() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            try {
                client.auth.signUpWith(Email) {
                    email = inputState.value.email
                    password = inputState.value.password
                }
                val user = client.auth.currentUserOrNull()
                if (user != null) {
                    client.from("accountDetails").upsert(
                        AccountDetailSer(
                            id = user.id,
                            email = inputState.value.email
                        )
                    )
                }
                _userState.value = UserState.Success("Sign up success")
            }catch (e: IOException) {
                _userState.value = UserState.Error("No Internet")
            }catch (e: SocketTimeoutException) {
                _userState.value = UserState.Error(
                    message = "Connection Timeout"
                )
            }catch (e: UnknownRestException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            }catch (e: HttpRequestException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            } catch (e: Exception) {
                _userState.value = UserState.Error("Could not sign in, please try again")
            }
        }
    }

    fun signIn() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            try {
                client.auth.signInWith(Email) {
                    email = inputState.value.email
                    password = inputState.value.password
                }
                _userState.value = UserState.Success("Sign in success")
            }catch (e: IOException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            }catch (e: SocketTimeoutException) {
                _userState.value = UserState.Error(
                    message = "Connection Timeout"
                )
            }catch (e: UnknownRestException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            }catch (e: HttpRequestException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            } catch (e: Exception) {
                _userState.value = UserState.Error("Sign in failed")
            }
        }
    }

    fun changeUserState(newState: UserState) {
        _userState.value = newState
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                client.auth.signOut()
                _userState.value = UserState.Success("Sign out success")
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "Sign out failed")
            } finally {
                client.close()
            }
        }
    }



    fun checkUser() {
        viewModelScope.launch {
            try {
                getClientSession()
                _userState.value = UserState.Success("User is signed in")
            } catch (e: YourNotLoggedInException) {
                _userState.value = UserState.NotLoggedIn
            } catch (e: IOException) {
                _userState.value = UserState.Error(message = "No Internet Connection")
            }catch (e: SocketTimeoutException) {
                _userState.value = UserState.Error(
                    message = "Connection Timeout"
                )
            }catch (e: UnknownRestException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            }catch (e: HttpRequestException) {
                _userState.value = UserState.Error(
                    message = "No Connection"
                )
            } catch (e: Exception) {
                _userState.value = UserState.NotLoggedIn
            }
        }
    }



    fun clearInput() {
        _inputState.value = AuthUIState(authType = _inputState.value.authType)
    }

    fun onEmailChange(email: String) {
        _inputState.value = _inputState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _inputState.value = _inputState.value.copy(password = password)
    }

    fun onAuthTypeChange() {
        _inputState.value = _inputState.value.copy(
            authType =
            if (_inputState.value.authType == AuthType.LOGIN) AuthType.SIGNUP
            else AuthType.LOGIN
        )
    }
}