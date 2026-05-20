package com.behnamuix.appointment.viewModel.removed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponse
import com.behnamuix.appointment.data.remote.remoteModel.people.ApiResponsePeople
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentRestoreRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleListRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleRestoreRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RemovedListViewModel(
    private val appointmentListRepo: AppointmentListRepo,
    private val appointmentRestoreRepo: AppointmentRestoreRepo,
    private val peopleListRepo: PeopleListRepo,
    private val peopleRestoreRepo: PeopleRestoreRepo,
) : ViewModel() {
    val _removedList = MutableStateFlow<ApiResponse?>(null)
    var removedList: StateFlow<ApiResponse?> = _removedList.asStateFlow()

    val _peopleRemovedList = MutableStateFlow<ApiResponsePeople?>(null)
    var peopleRemovedList: StateFlow<ApiResponsePeople?> = _peopleRemovedList.asStateFlow()

    private val _showToast = MutableSharedFlow<Boolean>()
    var showToast: SharedFlow<Boolean> = _showToast

    val tabs = listOf("appointment", "people")


    var msg = MutableStateFlow("")
    fun loadRemovedList() {
        viewModelScope.launch {
            _removedList.value = appointmentListRepo.getRemovedAppointmentList()
        }
    }

    fun loadPeopleRemovedList() {
        viewModelScope.launch {
            _peopleRemovedList.value = peopleListRepo.getRemovedPeopleList()
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

    fun restorePeople(id: Int) {
        viewModelScope.launch {
            val success = peopleRestoreRepo.restorePeople(id)
            //Kotlin object jadid misaze, va to State on jadid ro jaygozin mikoni.
            if (success?.success ?: false) {
                _showToast.emit(true)
                _peopleRemovedList.value = _peopleRemovedList.value?.let { resp ->
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