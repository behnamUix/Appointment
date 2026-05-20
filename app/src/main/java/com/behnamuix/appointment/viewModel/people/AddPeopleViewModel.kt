package com.behnamuix.appointment.viewModel.people

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleAddRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class AddPeopleViewModel(private val peopleAddRepo: PeopleAddRepo) : ViewModel() {
    var firestName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var phoneNumber = mutableStateOf("")
    var socialNumber = mutableStateOf("")

    var error = mutableStateOf(false)

    private val _showToast = MutableSharedFlow<Boolean>()
    var showToast: SharedFlow<Boolean> = _showToast
    var msg = MutableStateFlow("")

    fun checkValue() {
        if (firestName.value.isEmpty() || lastName.value.isEmpty() || phoneNumber.value.isEmpty() || socialNumber.value.isEmpty()) {
            error.value = true
        } else {
            error.value = false
        }
    }

    fun addPeople() {
        viewModelScope.launch {
            var resp = peopleAddRepo.addPeople(
                firestName.value,
                lastName.value,
                socialNumber.value,
                phoneNumber.value
            )
            if (resp?.success ?: false) {
                firestName.value = ""
                lastName.value = ""
                phoneNumber.value = ""
                socialNumber.value = ""

            }
        }
    }

}

