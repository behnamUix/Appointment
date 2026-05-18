package com.behnamuix.appointment.viewModel.appointment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddAppointmentViewModel : ViewModel() {
    var title = mutableStateOf("")
    var desc = mutableStateOf("")
    var selectedStartDate = mutableStateOf("No date selected")
    var selectedEndDateText = mutableStateOf("No date selected")

    fun save(){

    }

}