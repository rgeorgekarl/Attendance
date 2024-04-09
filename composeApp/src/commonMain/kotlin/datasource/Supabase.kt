package datasource

import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import org.real.SavePreferencesQueries


object Supabase {
    private const val URL = "https://qsdjdzqeqynijofbmdwa.supabase.co"
    private const val KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFzZGpkenFlcXluaWpvZmJtZHdhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDk5ODIwMzUsImV4cCI6MjAyNTU1ODAzNX0.RfSPl0N9QWrMt5NWjeQmf8_I06OplTM8ObYXFOgUto8"
    val client = createSupabaseClient(
        supabaseUrl = URL,
        supabaseKey = KEY
    ) {
        install(Postgrest)
        install(Auth)
        install(Storage)
        install(ComposeAuth)
    }
}

fun refreshCurrentSession(savePreferencesQueries: SavePreferencesQueries) {
    val getToken = savePreferencesQueries.getToken("accessToken").executeAsOneOrNull()
}