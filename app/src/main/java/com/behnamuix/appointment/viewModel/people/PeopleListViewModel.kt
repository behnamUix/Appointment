package com.behnamuix.appointment.viewModel.people

import androidx.lifecycle.ViewModel
import com.behnamuix.appointment.data.local.fake.fakePeoples
import com.behnamuix.appointment.data.local.model.FakePeople
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PeopleListViewModel : ViewModel() {
    var _peopleList = MutableStateFlow<List<FakePeople>>(emptyList())
    var peopleList: StateFlow<List<FakePeople>> = _peopleList.asStateFlow()
    fun loadFakeList() {
        _peopleList.value = fakePeoples
    }

}