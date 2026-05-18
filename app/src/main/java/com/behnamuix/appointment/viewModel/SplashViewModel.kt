package com.behnamuix.appointment.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behnamuix.appointment.const.SPLASH_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    var _goHome = MutableSharedFlow<Boolean>()
    var goHome: SharedFlow<Boolean> = _goHome



    fun showHomeSc(ctx: Context) {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
                _goHome.emit(true)

        }
    }


}