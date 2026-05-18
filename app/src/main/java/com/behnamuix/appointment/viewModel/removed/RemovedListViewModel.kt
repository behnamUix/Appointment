package com.behnamuix.appointment.viewModel.removed

import androidx.lifecycle.ViewModel
import com.behnamuix.appointment.data.local.fake.fakeRemovedAppointment
import com.behnamuix.appointment.data.local.model.FakeAppointment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RemovedListViewModel : ViewModel() {
    var _removedList = MutableStateFlow<List<FakeAppointment>>(emptyList())
    var removedList: StateFlow<List<FakeAppointment>> = _removedList.asStateFlow()
    fun loadFakeList() {
        _removedList.value = fakeRemovedAppointment
    }


}