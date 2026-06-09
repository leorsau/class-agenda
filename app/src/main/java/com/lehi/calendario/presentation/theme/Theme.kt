package com.lehi.calendario.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = ColorBoton,
    onPrimary = ColorTextoBoton,
    primaryContainer = ColorFondoTarjeta,
    background = ColorFondoPantalla,
    secondary = ColorTexfield,
    error = ColorError
)

@Composable
fun CalendarioTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}