package com.carsense.ai.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    background = backgroundDark,
    surface = surfaceDark,
    surfaceContainer = surfaceContainer,
    surfaceContainerHigh = surfaceContainerHigh,
    surfaceContainerHighest = surfaceContainerHighest,
    surfaceContainerLow = surfaceContainerLow,
    surfaceContainerLowest = surfaceContainerLowest,
    onBackground = onBackgroundDark,
    onSurface = onSurfaceDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outlineVariant = outlineVariantDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    error = errorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark
)


@Composable
fun CarSenseTheme(
    primaryColor: Color = primaryContainerDark,
    secondaryColor: Color = secondaryDark,
    content: @Composable () -> Unit
) {
    val dynamicColorScheme = DarkColorScheme.copy(
        primaryContainer = primaryColor,
        primary = primaryColor,
        onPrimaryContainer = Color.Black, // Assuming dark text on bright accent for contrast
        secondary = secondaryColor,
        secondaryContainer = secondaryColor,
        onSecondaryContainer = Color.Black
    )

    MaterialTheme(
        colorScheme = dynamicColorScheme,
        typography = CarSenseTypography,
        content = content
    )
}
