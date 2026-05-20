package com.behnamuix.appointment.data.remote.remoteModel.appointment

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val data: DataContainer? = null
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

@Serializable
data class DefaultResponse(
    val success: Boolean,
    val message: String? = "",
    val data: Int?
)

@Serializable
data class AddAppointmentResponse(
    val success: Boolean?,
    val message: String? = "",
    val data: Int?
)
