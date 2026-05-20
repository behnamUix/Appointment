package com.behnamuix.appointment.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.behnamuix.appointment.ui.theme.navigation.screens.HomeSc
import com.behnamuix.appointment.ui.theme.navigation.screens.SplashSc
import com.behnamuix.appointment.ui.theme.navigation.screens.appointment.AppointmentAddSc
import com.behnamuix.appointment.ui.navigation.screens.appointment.AppointmentListSc
import com.behnamuix.appointment.ui.theme.navigation.screens.people.PeopleAddSc
import com.behnamuix.appointment.ui.navigation.screens.people.PeopleListSc
import com.behnamuix.appointment.ui.theme.navigation.screens.removed.RemovedListSc

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route

    ) {
        composable(Screen.Splash.route) {
            SplashSc(navController)
        }
        composable(Screen.Home.route) {
            HomeSc(navController)
        }
        composable(Screen.PeopleAdd.route) {
            PeopleAddSc(
                navController
            )
        }
        composable(Screen.PeopleList.route) {
            PeopleListSc(navController,
                onItemClick = { personId ->
                navController.navigate(Screen.AppointmentAdd.createRoute(personId))
            })
        }
        composable(Screen.AppointmentAdd.route) {
            AppointmentAddSc(navController,)
        }
        composable(Screen.AppointmentList.route) {
            AppointmentListSc(navController)
        }
        composable(Screen.RemovedList.route) {
            RemovedListSc(navController)
        }


    }
}
