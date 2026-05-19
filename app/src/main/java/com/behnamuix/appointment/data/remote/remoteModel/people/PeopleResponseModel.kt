package com.behnamuix.appointment.data.remote.remoteModel.people

import kotlinx.serialization.Serializable

//============
@Serializable
data class ApiResponsePeople(
    val success: Boolean,
    val message: String?,
    val data: PeopleList?
)

@Serializable
data class PeopleList(
    val data: List<PeopleData>,
    val totalCount: Int,
    val filteredCount: Int
)

@Serializable
data class PeopleData(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val isDeleted: Boolean,
    val deleteTime: String?, // یا اگر فرمت خاصی دارد، از LocalDateTime استفاده کنید
    val phoneNumber: String,
    val socialNumber: String,
    val deleteReason: String?,
    val appointmentsCount: Int
)

