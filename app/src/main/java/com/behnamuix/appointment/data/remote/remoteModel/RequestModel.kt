package com.behnamuix.appointment.data.remote.remoteModel

import kotlinx.serialization.Serializable

//request
@Serializable
data class ApiRequest(
    var start: Int = 0,
    var lenght: Int = 100,
    var removedState: Int = 0,
    var from: Int = 0,
    var to: Int = 0,
    var title: String = "string",
    var personId: Int = 0
)
@Serializable
data class AppointmentDeleteRequest(
    val id: Int,
    val deleteReason: String
)