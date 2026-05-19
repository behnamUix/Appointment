package com.behnamuix.appointment.ui.theme.navigation


sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")

    object AppointmentList : Screen("appointment_list")
    object AppointmentAdd : Screen("appointment_add/{personId}") {
        fun createRoute(personId: Int) = "appointment_add/$personId"
    }

    object PeopleAdd : Screen("people_add")
    object PeopleList : Screen("people_list")

    object RemovedList : Screen("removed_list")
}


