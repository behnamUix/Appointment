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
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    var data: DataContainer
)

@Serializable
data class DataContainer(
    val data: List<Item>,
    val totalCount: Int,
    val filteredCount: Int
)

@Serializable
data class Item(
    val id: Int,
    val title: String,
    val endTime: String,
    val personId: Int,
    val startTime: String,
    val isDeleted: Boolean,
    val deleteTime: String?,
    val description: String,
    val deleteReason: String?,
    val phoneNumber: String,
    val personName: String
)

