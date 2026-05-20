package com.behnamuix.appointment.data.remote.remoteRepo

import android.util.Log
import com.behnamuix.appointment.const.PEOPLE_ADD_URL
import com.behnamuix.appointment.data.remote.remoteModel.AddPeopleRequest
import com.behnamuix.appointment.data.remote.remoteModel.appointment.DefaultResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PeopleAddRepo(private val client: HttpClient) {
    suspend fun addPeople(
        firstName: String,
        lastName: String,
        socialNumber: String,
        phoneNumber: String
    ): DefaultResponse? {
        val addPeopleReq = AddPeopleRequest(firstName, lastName, socialNumber, phoneNumber)
        return try {
            client.post(PEOPLE_ADD_URL) {
                setBody(addPeopleReq)
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            Log.d("TAG", e.message.toString())
            null
        }

    }
}
