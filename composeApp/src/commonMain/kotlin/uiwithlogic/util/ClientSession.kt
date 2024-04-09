package uiwithlogic.util

import datasource.Supabase.client
import exceptions.YourNotLoggedInException
import io.github.jan.supabase.gotrue.auth
import org.real.SavePreferencesQueries

suspend fun getClientSession(
    savePreferencesQueries: SavePreferencesQueries,
) {
    val getToken = getToken(savePreferencesQueries)
    if (getToken != null) {
        client.auth.retrieveUser(getToken)
        client.auth.refreshCurrentSession()
    } else {
        throw YourNotLoggedInException()
    }
}

fun getToken(
    queries: SavePreferencesQueries,
): String? = queries.getToken("accessToken").executeAsOneOrNull()?.data_


fun savePreferences(
    savePreferencesQueries: SavePreferencesQueries,
) {
    val accessToken = client.auth.currentAccessTokenOrNull()
    savePreferencesQueries.insertOrReplace(key = "accessToken", data_ = accessToken)
}