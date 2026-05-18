package com.behnamuix.appointment.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MaterialBluePrimary,
    secondary = MaterialYellowError,
    background =MaterialBlackBackground,
    onPrimary = MaterialWhiteText,
    onSecondary = MaterialBlackBackground
)



@Composable
fun MyApplication03Theme(
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