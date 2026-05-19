package com.behnamuix.appointment.ui.theme.navigation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.behnamuix.appointment.R
import com.behnamuix.appointment.ui.theme.navigation.Screen

import com.behnamuix.appointment.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import kotlin.system.exitProcess

@Composable
fun HomeSc(navController: NavHostController, homeViewModel: HomeViewModel = koinViewModel()) {
    val showExitCard = homeViewModel.showExitCard
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        homeViewModel.checkInternet(context)
        homeViewModel.showCard()
        showExitCard.collect {
            if (it) {
                homeViewModel.show.value = it
            }
        }

    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ToolbarComp()
        ConnectStatusComp(homeViewModel)
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .animateContentSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                FuncCardComp("appointments", R.drawable.icon_apointment) {
                    navController.navigate(Screen.AppointmentList.route)

                }
                FuncCardComp("people", R.drawable.icon_user) {
                    navController.navigate(Screen.PeopleList.route)

                }
                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    if (homeViewModel.show.value) {
                        FuncCardSmall("Exit", R.drawable.icon_exit, true) {
                            exitProcess(0)
                        }
                    }
                    Spacer(Modifier.weight(1f))
                    FuncCardSmall("removed", R.drawable.icon_delete) {
                        navController.navigate(Screen.RemovedList.route)

                    }
                }
                Text(
                    "develop and design by behnamUix",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
                )

            }

        }
    }


}

@Composable
fun ConnectStatusComp(homeViewModel: HomeViewModel) {
    Text(
        if (homeViewModel.connectStatus.value) {
            "online"
        } else {
            ""
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.labelLarge,
        color = Color(0xFF4CAF50).copy(0.5f)

    )
}

@Composable
fun ToolbarComp() {
    Spacer(modifier = Modifier.height(24.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.icon_api),
            contentDescription = "",
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("A")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimary)) {

                    append("ppointment")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimary)) {

                }
            },
            style = MaterialTheme.typography.headlineSmall
        )

    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun FuncCardComp(title: String, icon: Int, clickedOnCard: () -> Unit) {
    OutlinedCard(
        onClick = { clickedOnCard() },
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {

            Image(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            Text(

                text = title,
                style = MaterialTheme.typography.headlineSmall,

                )
        }


    }

}

@Composable
fun FuncCardSmall(
    title: String, icon: Int,
    exit: Boolean = false,
    clickedOnCard: () -> Unit
) {
    if (exit) {
        Card(
            onClick = { clickedOnCard() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    painter = painterResource(icon),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    title,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall
                )
            }


        }

    } else {
        OutlinedCard(
            onClick = { clickedOnCard() },
            elevation = CardDefaults.elevatedCardElevation(8.dp),

            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    tint = Color.White.copy(0.4f),
                    painter = painterResource(icon),
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    title,
                    style = MaterialTheme.typography.bodyLarge,

                    )
            }


        }

    }

}