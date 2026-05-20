package com.behnamuix.appointment.data.remote.remoteRepo

import android.util.Log
import com.behnamuix.appointment.const.APPOINTMENT_ADD_URL
import com.behnamuix.appointment.data.remote.remoteModel.AddAppointmentRequest
import com.behnamuix.appointment.data.remote.remoteModel.appointment.AddAppointmentResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppointmentAddRepo(private val client: HttpClient) {
    suspend fun addAppointment(
        personid: Int,
        startTime: Int,
        endTime: Int,
        title: String,
        des: String
    ): AddAppointmentResponse? {
        val addReq = AddAppointmentRequest(personid, startTime, endTime, title, des)
        return try {
            client.post(APPOINTMENT_ADD_URL) {
                setBody(addReq)
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("ERROR", e.message.toString())
            null
        }
    }
}