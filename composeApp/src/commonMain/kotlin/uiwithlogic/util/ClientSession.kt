package uiwithlogic.util

import datasource.Supabase.client
import exceptions.YourNotLoggedInException
import io.github.jan.supabase.gotrue.auth

suspend fun getClientSession() {
    val getToken = client.auth.sessionManager.loadSession()?.accessToken ?: throw YourNotLoggedInException()
    client.auth.retrieveUser(getToken)
    client.auth.refreshCurrentSession()
}
