package com.behnamuix.appointment.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = MaterialBluePrimary,
    secondary = MaterialYellowError,
    background =MaterialBlackBackground,
    onPrimary = MaterialWhiteText,
    onSecondary = MaterialBlackBackground
)



@Composable
fun AppointmentTheme(
    darkTheme: Boolean =true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}