package com.example.lykkehjulet.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = darkpurple,
    primaryVariant = lightpurple,
    secondary = Teal200,
    secondaryVariant = secondaryvariant
)

@Composable
fun LykkeHjuletTheme(
    content: @Composable () -> Unit
) {
    val colors = DarkColorPalette


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
