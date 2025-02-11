package com.vikvita.music_player.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.vikvita.uikit.theme.backgroundDark
import com.vikvita.uikit.theme.backgroundLight
import com.vikvita.uikit.theme.errorContainerDark
import com.vikvita.uikit.theme.errorContainerLight
import com.vikvita.uikit.theme.errorDark
import com.vikvita.uikit.theme.errorLight
import com.vikvita.uikit.theme.inverseOnSurfaceDark
import com.vikvita.uikit.theme.inverseOnSurfaceLight
import com.vikvita.uikit.theme.inversePrimaryDark
import com.vikvita.uikit.theme.inversePrimaryLight
import com.vikvita.uikit.theme.inverseSurfaceDark
import com.vikvita.uikit.theme.inverseSurfaceLight
import com.vikvita.uikit.theme.onBackgroundDark
import com.vikvita.uikit.theme.onBackgroundLight
import com.vikvita.uikit.theme.onErrorContainerDark
import com.vikvita.uikit.theme.onErrorContainerLight
import com.vikvita.uikit.theme.onErrorDark
import com.vikvita.uikit.theme.onErrorLight
import com.vikvita.uikit.theme.onPrimaryContainerDark
import com.vikvita.uikit.theme.onPrimaryContainerLight
import com.vikvita.uikit.theme.onPrimaryDark
import com.vikvita.uikit.theme.onPrimaryLight
import com.vikvita.uikit.theme.onSecondaryContainerDark
import com.vikvita.uikit.theme.onSecondaryContainerLight
import com.vikvita.uikit.theme.onSecondaryDark
import com.vikvita.uikit.theme.onSecondaryLight
import com.vikvita.uikit.theme.onSurfaceDark
import com.vikvita.uikit.theme.onSurfaceLight
import com.vikvita.uikit.theme.onSurfaceVariantDark
import com.vikvita.uikit.theme.onSurfaceVariantLight
import com.vikvita.uikit.theme.onTertiaryContainerDark
import com.vikvita.uikit.theme.onTertiaryContainerLight
import com.vikvita.uikit.theme.onTertiaryDark
import com.vikvita.uikit.theme.onTertiaryLight
import com.vikvita.uikit.theme.outlineDark
import com.vikvita.uikit.theme.outlineLight
import com.vikvita.uikit.theme.outlineVariantDark
import com.vikvita.uikit.theme.outlineVariantLight
import com.vikvita.uikit.theme.primaryContainerDark
import com.vikvita.uikit.theme.primaryContainerLight
import com.vikvita.uikit.theme.primaryDark
import com.vikvita.uikit.theme.primaryLight
import com.vikvita.uikit.theme.scrimDark
import com.vikvita.uikit.theme.scrimLight
import com.vikvita.uikit.theme.secondaryContainerDark
import com.vikvita.uikit.theme.secondaryContainerLight
import com.vikvita.uikit.theme.secondaryDark
import com.vikvita.uikit.theme.secondaryLight
import com.vikvita.uikit.theme.surfaceBrightDark
import com.vikvita.uikit.theme.surfaceBrightLight
import com.vikvita.uikit.theme.surfaceContainerDark
import com.vikvita.uikit.theme.surfaceContainerHighDark
import com.vikvita.uikit.theme.surfaceContainerHighLight
import com.vikvita.uikit.theme.surfaceContainerHighestDark
import com.vikvita.uikit.theme.surfaceContainerHighestLight
import com.vikvita.uikit.theme.surfaceContainerLight
import com.vikvita.uikit.theme.surfaceContainerLowDark
import com.vikvita.uikit.theme.surfaceContainerLowLight
import com.vikvita.uikit.theme.surfaceContainerLowestDark
import com.vikvita.uikit.theme.surfaceContainerLowestLight
import com.vikvita.uikit.theme.surfaceDark
import com.vikvita.uikit.theme.surfaceDimDark
import com.vikvita.uikit.theme.surfaceDimLight
import com.vikvita.uikit.theme.surfaceLight
import com.vikvita.uikit.theme.surfaceVariantDark
import com.vikvita.uikit.theme.surfaceVariantLight
import com.vikvita.uikit.theme.tertiaryContainerDark
import com.vikvita.uikit.theme.tertiaryContainerLight
import com.vikvita.uikit.theme.tertiaryDark
import com.vikvita.uikit.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Composable
fun MusicPlayerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}