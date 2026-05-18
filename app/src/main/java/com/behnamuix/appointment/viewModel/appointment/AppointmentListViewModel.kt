package com.behnamuix.appointment.viewModel.appointment

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.ApiResponse
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.utils.isInternetAvailable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppointmentListViewModel(
    val appointmentListRepo: AppointmentListRepo,
    val appointmentDeleteRepo: AppointmentDeleteRepo
) : ViewModel() {

    private var _appointmentList = MutableStateFlow<ApiResponse?>(null)
    val appointmentList: StateFlow<ApiResponse?> = _appointmentList.asStateFlow()

    var connectStatus = mutableStateOf(false)

    var id = mutableIntStateOf(0)

    fun appointmentLoad() {
        viewModelScope.launch {
            _appointmentList.value = appointmentListRepo.getAppointmentList()
        }
    }

    fun checkInternet(ctx: Context) {
        connectStatus.value = isInternetAvailable(ctx)
    }

    fun appointmentDelete(id: Int, reason: String) {
        viewModelScope.launch {
            appointmentDeleteRepo.delete(id, reason)

        }

    }
}