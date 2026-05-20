package com.behnamuix.appointment.viewModel.removed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponse
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentRestoreRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RemovedListViewModel(
    private val appointmentListRepo: AppointmentListRepo,
    private val appointmentRestoreRepo: AppointmentRestoreRepo
) : ViewModel() {
    val _removedList = MutableStateFlow<ApiResponse?>(null)
    var removedList: StateFlow<ApiResponse?> = _removedList.asStateFlow()

    private val _showToast = MutableSharedFlow<Boolean>()
    var showToast: SharedFlow<Boolean> = _showToast

    var msg = MutableStateFlow("")
    fun loadRemovedList() {
        viewModelScope.launch {
            _removedList.value = appointmentListRepo.getRemovedAppointmentList()
        }
    }

    fun restoreAppointment(id: Int) {
        viewModelScope.launch {
            val success = appointmentRestoreRepo.restoreAppointment(id)
            //Kotlin object jadid misaze, va to State on jadid ro jaygozin mikoni.
            if (success?.success ?: false) {
                _showToast.emit(true)
                _removedList.value = _removedList.value?.let { resp ->
                    resp.copy(
                        data = resp.data?.copy(
                            data = resp.data.data.filter { it.id != id }
                        )
                    )
                }
            }
        }
    }


}