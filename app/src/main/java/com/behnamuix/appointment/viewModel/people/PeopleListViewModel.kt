package com.behnamuix.appointment.viewModel.people

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.people.ApiResponsePeople
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleListRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PeopleListViewModel(val peopleListRepo: PeopleListRepo, val peopleDeleteRepo: PeopleDeleteRepo) :
    ViewModel() {
    var _peopleList = MutableStateFlow<ApiResponsePeople?>(null)
    var peopleList: StateFlow<ApiResponsePeople?> = _peopleList.asStateFlow()

    val openAlertDialog = mutableStateOf(false)

    var itemId = mutableIntStateOf(0)

    fun loadPeopleList() {
        viewModelScope.launch {
            _peopleList.value = peopleListRepo.getPeopleList()
        }
    }



    fun peopleDelete(id: Int, reason: String) {
        viewModelScope.launch {
            val success = peopleDeleteRepo.delete(id, reason)
            if (success.success) {
                _peopleList.value = _peopleList.value?.let { response ->
                    response.copy(
                        data = response.data?.copy(
                            data = response.data.data.filter { it.id != id }
                        )
                    )
                }
            }
        }
    }

}