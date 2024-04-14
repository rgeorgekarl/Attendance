package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import theme.colors.*




val ThemeColor = compositionLocalOf {
    mutableStateOf(1)
}
val LocalDark = compositionLocalOf {
    mutableStateOf(false)
}
@Composable
fun AttendanceTheme(
    content: @Composable() () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val darkState = remember { mutableStateOf(isDark) }
    val themeColor = remember { mutableStateOf(1) }
    val themeState = themeColor.value
    CompositionLocalProvider(
        ThemeColor provides themeColor,
        LocalDark provides darkState
    ) {
        val useDarkTheme by darkState
        val colors = when (themeState) {
            1 -> if (useDarkTheme) Color1.DarkColors else Color1.LightColors
            2 -> if (useDarkTheme) Color2.DarkColors else Color2.LightColors
            3 -> if (useDarkTheme) Color3.DarkColors else Color3.LightColors
            4 -> if (useDarkTheme) Color4.DarkColors else Color4.LightColors
            5 -> if (useDarkTheme) Color5.DarkColors else Color5.LightColors
            6 -> if (useDarkTheme) Color6.DarkColors else Color6.LightColors
            7 -> if (useDarkTheme) Color7.DarkColors else Color7.LightColors
            8 -> if (useDarkTheme) Color8.DarkColors else Color8.LightColors
            else -> if (useDarkTheme) Color9.DarkColors else Color9.LightColors
        }

        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}