package com.behnamuix.appointment.data.remote.remoteRepo

import com.behnamuix.appointment.const.APPOINTMENT_DELETE_URL
import com.behnamuix.appointment.const.PEOPLE_DELETE_URL
import com.behnamuix.appointment.data.remote.remoteModel.DeleteRequest
import com.behnamuix.appointment.data.remote.remoteModel.appointment.DeleteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PeopleDeleteRepo(var client: HttpClient) {
    suspend fun delete(id: Int, reason: String): DeleteResponse {
        val peopleDeleteReq = DeleteRequest(id, reason)
        return client.post(PEOPLE_DELETE_URL) {
            setBody(peopleDeleteReq)
            contentType(ContentType.Application.Json)
        }.body()

    }
}