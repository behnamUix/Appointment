package com.behnamuix.appointment.viewModel.removed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.local.fake.fakeRemovedAppointment
import com.behnamuix.appointment.data.local.model.FakeAppointment
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponse
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RemovedListViewModel(private val appointmentListRepo: AppointmentListRepo) : ViewModel() {
    var _removedList = MutableStateFlow<ApiResponse?>(null)
    var removedList: StateFlow<ApiResponse?> = _removedList.asStateFlow()
    fun loadRemovedList() {
        viewModelScope.launch {
            _removedList.value = appointmentListRepo.getRemovedAppointmentList()
        }
    }


}