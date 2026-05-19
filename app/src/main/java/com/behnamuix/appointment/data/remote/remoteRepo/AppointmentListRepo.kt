package com.behnamuix.appointment.data.remote.remoteRepo

import android.util.Log
import com.behnamuix.appointment.const.APPOINTMENT_LIST_URL
import com.behnamuix.appointment.data.remote.remoteModel.ApiRequest
import com.behnamuix.appointment.data.remote.remoteModel.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AppointmentListRepo(private val client: HttpClient) {
    val apiRequest = ApiRequest(start = 0, lenght = 100, removedState = 0)
    suspend fun getAppointmentList(): ApiResponse? {
        return try {
            client.post(APPOINTMENT_LIST_URL) {
                setBody(apiRequest)
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAg", e.toString())

            null
        }
    }

}
