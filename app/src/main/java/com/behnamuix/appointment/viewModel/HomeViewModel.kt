package com.behnamuix.appointment.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.const.SHOW_EXIT_CARD_DELAY
import com.behnamuix.appointment.utils.isInternetAvailable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var _showExitCard = MutableSharedFlow<Boolean>()
    var showExitCard: SharedFlow<Boolean> = _showExitCard

    var show = mutableStateOf<Boolean>(false)

    var connectStatus = mutableStateOf(false)

    fun showCard() {
        viewModelScope.launch {
            delay(SHOW_EXIT_CARD_DELAY)
            _showExitCard.emit(true)
        }
    }
    fun checkInternet(ctx: Context){
        connectStatus.value=isInternetAvailable(ctx)
    }
}