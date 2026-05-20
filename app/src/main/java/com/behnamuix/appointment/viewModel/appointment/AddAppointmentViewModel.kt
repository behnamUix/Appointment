package com.behnamuix.appointment.viewModel.appointment

import android.util.Log
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentAddRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class AddAppointmentViewModel(private val addRepo: AppointmentAddRepo) : ViewModel() {

    var id = mutableStateOf(0)

    var title = mutableStateOf("")
    var desc = mutableStateOf("")
    var selectedStartDate = mutableStateOf(0)
    var selectedEndDate = mutableStateOf(0)

    var _showToast = MutableSharedFlow<Boolean>()
    var showToast: SharedFlow<Boolean> = _showToast

    var toastMsg = mutableStateOf("")

    var startDate = mutableStateOf("")
    var endDate = mutableStateOf("")

    fun addAppointment(personId: String?) {
        viewModelScope.launch {
            try {
                var resp = addRepo.addAppointment(
                    personId?.toInt() ?: 0,
                    selectedStartDate.value,
                    selectedEndDate.value,
                    title.value,
                    desc.value
                )
                if (resp?.success!!) {
                    Log.d("RESP", "OK")
                    _showToast.emit(true)
                    setToastMsg("OK")

                } else {
                    _showToast.emit(true)
                    setToastMsg("Error in fetch data!")


                }
            } catch (e: Exception) {
                Log.d("RESP", "ERR")
                _showToast.emit(true)
                setToastMsg("ERR:${e.message}")

            }

        }

    }

    fun checkValue() {
        viewModelScope.launch {
            if (title.value.isEmpty()) {
                _showToast.emit(true)
                setToastMsg("Error: filed is empty!")
            }
            if (desc.value.isEmpty()) {
                _showToast.emit(true)
                setToastMsg("Error: filed is empty!")

            }
            if (id.value == 0) {
                _showToast.emit(true)
                setToastMsg("Error: person not set!")

            }
        }

    }

    fun setToastMsg(text: String) {
        toastMsg.value = text
    }

}