package com.behnamuix.appointment.viewModel.appointment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponseGetAppointment
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentGetRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppointmentGetViewModel(
    val appointmentGetRepo: AppointmentGetRepo,
) : ViewModel() {
    val _appointment = MutableStateFlow<ApiResponseGetAppointment?>(null)
    val appointment: StateFlow<ApiResponseGetAppointment?> = _appointment.asStateFlow()

    fun getAppointment(id: Int) {
        viewModelScope.launch {
            _appointment.value = appointmentGetRepo.getAppointment(id)

        }
    }
}

