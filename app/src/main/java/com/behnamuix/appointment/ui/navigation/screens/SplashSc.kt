package com.behnamuix.appointment.ui.theme.navigation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.ui.theme.navigation.Routes
import com.behnamuix.appointment.viewModel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashSc(
    navController: NavHostController,
    splashVm: SplashViewModel = koinViewModel()
) {
    val ctx = LocalContext.current
    val goHome = splashVm.goHome
    val infiniteState = rememberInfiniteTransition()
    var stateIcon by remember { mutableStateOf(-200.dp) }
    var stateText by remember { mutableStateOf(800.dp) }

    val iconTranslateX by animateDpAsState(
        targetValue = stateIcon,
        animationSpec = tween(3000, delayMillis = 1000)
    )
    val textTranslateX by animateDpAsState(
        targetValue = stateText,
        animationSpec = tween(2000)
    )

    val alphaIcon by infiniteState.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = LinearEasing,
                delayMillis = 500
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        stateIcon = 0.dp
        stateText = 0.dp
        splashVm.showHomeSc(ctx)
        goHome.collect {
            if (it) {
                navController.navigate(Routes.HOME)

            }
        }


    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.icon_api),
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .offset(x = iconTranslateX)
                    .alpha(alphaIcon)
            )
            Text(
                "Appointment",
                modifier = Modifier.offset(x = textTranslateX),
                style = MaterialTheme.typography.headlineSmall
            )


        }

    }

}