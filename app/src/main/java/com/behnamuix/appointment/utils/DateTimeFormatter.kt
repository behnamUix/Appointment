package com.behnamuix.appointment.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

fun dateTimeFormat(unixTimeMillis: Long): String {
    val tehranTimeZone = TimeZone.getTimeZone("Asia/Tehran")
    val formatter = SimpleDateFormat(
        "EEE yyyy-MM-dd | HH:mm:ss",
    )

    formatter.timeZone = tehranTimeZone
    val date = Date(unixTimeMillis)
    return formatter.format(date)
}