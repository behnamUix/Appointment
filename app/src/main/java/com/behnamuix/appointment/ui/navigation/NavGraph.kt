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
import com.behnamuix.appointment.ui.theme.navigation.screens.people.PeopleListSc
import com.behnamuix.appointment.ui.theme.navigation.screens.removed.RemovedListSc

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.APPOINTMENT_LIST

    ) {
        composable(Routes.SPLASH) {
            SplashSc(navController)
        }
        composable(Routes.HOME) {
            HomeSc(navController)
        }
        composable(Routes.PEOPLE_ADD) {
            PeopleAddSc(navController)
        }
        composable(Routes.PEOPLE_LIST) {
            PeopleListSc(navController)
        }
        composable(Routes.APPOINTMENT_ADD) {
            AppointmentAddSc(navController)
        }
        composable(Routes.APPOINTMENT_LIST) {
            AppointmentListSc(navController)
        }
        composable(Routes.REMOVED_LIST) {
            RemovedListSc(navController)
        }


    }
}
