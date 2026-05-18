package com.behnamuix.appointment.viewModel.people

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddPeopleViewModel : ViewModel() {
    var firestName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var phoneNumber = mutableStateOf("")
    var socialNumber = mutableStateOf("")

    var error = mutableStateOf(false)

    fun checkValue() {
            if (firestName.value.isEmpty() || lastName.value.isEmpty() || phoneNumber.value.isEmpty() || socialNumber.value.isEmpty()) {
                error.value=true
            } else {
                error.value=false
            }
    }

    fun save() {

    }

}