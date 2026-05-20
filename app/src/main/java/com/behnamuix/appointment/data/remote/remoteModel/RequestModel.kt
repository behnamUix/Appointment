package com.behnamuix.appointment.data.remote.remoteModel

import kotlinx.serialization.Serializable

//request
@Serializable
data class ApiRequest(
    var start: Int = 0,
    var lenght: Int,
    var removedState: Int = 0,
)

@Serializable
data class DeleteRequest(
    val Id: Int,
    val deleteReason: String
)
@Serializable
data class AddAppointmentRequest(
    val personId: Int = 0,
    val startTime: Int = 0,
    val endTime: Int = 0,
    val title: String? = "",
    val description: String? = ""
)

@Serializable
data class AddPeopleRequest(
    val firstName:String,
    val lastName:String,
    val socialNumber:String,
    val phoneNumber:String
)


