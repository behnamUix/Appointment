package com.behnamuix.appointment.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.const.SPLASH_DELAY
import com.behnamuix.appointment.utils.isInternetAvailable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    var _goHome = MutableSharedFlow<Boolean>()
    var goHome: SharedFlow<Boolean> = _goHome

    var connectStatus = mutableStateOf(false)


    fun showHomeSc(ctx: Context) {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
                _goHome.emit(true)

        }
    }
    fun checkInternet(ctx: Context){
        connectStatus.value=isInternetAvailable(ctx)
    }


}