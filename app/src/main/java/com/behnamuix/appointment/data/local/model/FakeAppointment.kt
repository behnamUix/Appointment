package com.behnamuix.appointment.data.local.model

data class FakeAppointment(
    val id: Int=0,
    val title: String="",
    val endTime: String="",
    val personId: Int=0,
    val startTime: String="",
    val isDeleted: Boolean=false,
    val deleteTime: String?="",
    val description: String="",
    val deleteReason: String?="",
    val phoneNumber: String="",
    val personName: String=""
)
