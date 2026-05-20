package com.behnamuix.appointment.utils

import kotlinx.coroutines.flow.MutableStateFlow

fun setMessage(value: String,msg: MutableStateFlow<String>) {
    msg.value = value
}