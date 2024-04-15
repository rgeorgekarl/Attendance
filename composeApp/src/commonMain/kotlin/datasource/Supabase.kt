package datasource

import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage


object Supabase {
    private const val URL = "https://twqqgpjdvichfwlwrsra.supabase.co"
    private const val KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR3cXFncGpkdmljaGZ3bHdyc3JhIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTMxODAwNzEsImV4cCI6MjAyODc1NjA3MX0.iBjSF6yenp7Cp8cf0yAWTcAU_YMSAROd693jPKPVFN4"
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

