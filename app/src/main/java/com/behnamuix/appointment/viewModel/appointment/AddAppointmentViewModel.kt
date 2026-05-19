package com.behnamuix.appointment.viewModel.appointment

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentAddRepo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class AddAppointmentViewModel(private val addRepo: AppointmentAddRepo) : ViewModel() {
    var id = mutableIntStateOf(1014)

    var title = mutableStateOf("")
    var desc = mutableStateOf("")
    var selectedStartDate = mutableStateOf(0)
    var selectedEndDateText = mutableStateOf(0)

    var _showToast = MutableSharedFlow<Boolean>()
    var showToast: SharedFlow<Boolean> = _showToast

    var toastMsg=mutableStateOf("")

    fun save(
        personid: Int,
        startTime: Int,
        endTime: Int,
        title: String,
        des: String
    ) {
        viewModelScope.launch {
            try {
                var resp=addRepo.addAppointment(personid, startTime, endTime, title, des)
                if(resp?.success!!){
                    Log.d("RESP","OK")
                    _showToast.emit(true)
                    setToastMsg("OK")
                }else{
                    _showToast.emit(true)
                    setToastMsg("Error in fetch data!")


                }
            }catch (e: Exception){
                Log.d("RESP","ERR")
                _showToast.emit(true)
                setToastMsg("Error in fetch data!")

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
            if (id.intValue == 0) {
                _showToast.emit(true)
                setToastMsg("Error: person not set!")

            }
        }

    }
    fun setToastMsg(text:String){
        toastMsg.value=text
    }

}