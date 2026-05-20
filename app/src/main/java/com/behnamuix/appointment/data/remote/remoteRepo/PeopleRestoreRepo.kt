package com.behnamuix.appointment.data.remote.remoteRepo

import com.behnamuix.appointment.const.APPOINTMENT_RESTORE_URL
import com.behnamuix.appointment.const.PEOPLE_RESTORE_URL
import com.behnamuix.appointment.data.remote.remoteModel.appointment.DefaultResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PeopleRestoreRepo(private val client: HttpClient) {
    suspend fun restorePeople(id: Int): DefaultResponse? {
        return try {
            client.post(PEOPLE_RESTORE_URL) {
                url{
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