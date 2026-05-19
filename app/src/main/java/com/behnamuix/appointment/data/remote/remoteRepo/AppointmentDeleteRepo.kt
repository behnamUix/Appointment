package com.behnamuix.appointment.data.remote.remoteRepo

import com.behnamuix.appointment.const.APPOINTMENT_DELETE_URL
import com.behnamuix.appointment.data.remote.remoteModel.DeleteRequest
import com.behnamuix.appointment.data.remote.remoteModel.DeleteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppointmentDeleteRepo(var client: HttpClient) {
    suspend fun delete(id: Int, reason: String): DeleteResponse {
        val appointmentDeleteReq = DeleteRequest(id, reason)
        return client.post(APPOINTMENT_DELETE_URL) {
            setBody(appointmentDeleteReq)
            contentType(ContentType.Application.Json)
        }.body()

    }
}