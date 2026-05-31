package com.behnamuix.appointment.data.remote.remoteRepo

import com.behnamuix.appointment.const.APPOINTMENT_GET_URL
import com.behnamuix.appointment.const.APPOINTMENT_RESTORE_URL
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponse
import com.behnamuix.appointment.data.remote.remoteModel.appointment.ApiResponseGetAppointment
import com.behnamuix.appointment.data.remote.remoteModel.appointment.DefaultResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppointmentGetRepo(private val client: HttpClient) {
    suspend fun getAppointment(id: Int): ApiResponseGetAppointment? {
        return try {
            client.post(APPOINTMENT_GET_URL) {
                url {
                    parameters.append("Id", id.toString())
                }
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}