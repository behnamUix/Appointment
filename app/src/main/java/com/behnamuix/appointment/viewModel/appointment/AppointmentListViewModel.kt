package com.behnamuix.appointment.viewModel.appointment

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponse
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.utils.isInternetAvailable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppointmentListViewModel(
    val appointmentListRepo: AppointmentListRepo,
    val appointmentDeleteRepo: AppointmentDeleteRepo
) : ViewModel() {

    private var _appointmentList = MutableStateFlow<ApiResponse?>(null)
    val appointmentList: StateFlow<ApiResponse?> = _appointmentList.asStateFlow()

    private val _showToast = MutableSharedFlow<Boolean>()
    var showToast: SharedFlow<Boolean> = _showToast

    var msg = MutableStateFlow("")
    var connectStatus = mutableStateOf(false)

    var itemId = mutableIntStateOf(0)

    val openAlertDialog = mutableStateOf(false)

    fun appointmentLoad() {
        viewModelScope.launch {
            _appointmentList.value = appointmentListRepo.getAppointmentList()
            Log.e("SIZE", appointmentList.value?.data?.data?.size.toString())

        }
    }

    fun appointmentDelete(id: Int, reason: String) {
        viewModelScope.launch {
            val success = appointmentDeleteRepo.delete(id, reason)
            if (success.success) {
                _showToast.emit(true)
                _appointmentList.value = _appointmentList.value?.let { response ->
                    response.copy(
                        data = response.data?.copy(
                            data = response.data.data.filter { it.id != id }
                        )
                    )
                }
            }
        }
    }

    fun checkInternet(ctx: Context) {
        connectStatus.value = isInternetAvailable(ctx)
    }


}

