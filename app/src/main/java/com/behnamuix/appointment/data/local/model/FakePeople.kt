package com.behnamuix.appointment.data.local.model

data class FakePeople(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val isDeleted: Boolean,
    val deleteTime: String?, // یا زمان واقعی اگر دارید، مثلا LocalDateTime
    val phoneNumber: String,
    val socialNumber: String,
    val deleteReason: String?,
    val appointmentsCount: Int
)