package com.behnamuix.appointment.viewModel.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.data.remote.remoteModel.people.ApiResponsePeople
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleListRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PeopleListViewModel(val peopleRepo: PeopleListRepo) : ViewModel() {
    var _peopleList = MutableStateFlow<ApiResponsePeople?>(null)
    var peopleList: StateFlow<ApiResponsePeople?> = _peopleList.asStateFlow()
    fun loadPeopleList() {
        viewModelScope.launch {
            _peopleList.value = peopleRepo.getPeopleList()
        }
    }

}