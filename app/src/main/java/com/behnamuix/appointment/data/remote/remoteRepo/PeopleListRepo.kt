package com.behnamuix.appointment.data.remote.remoteRepo

import android.util.Log
import com.behnamuix.appointment.const.PEOPLE_LIST_URL
import com.behnamuix.appointment.data.remote.remoteModel.ApiRequest
import com.behnamuix.appointment.data.remote.remoteModel.people.ApiResponsePeople
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PeopleListRepo(private val client: HttpClient) {
    suspend fun getPeopleList(): ApiResponsePeople? {
        val peopleListReq = ApiRequest(start = 0, lenght = 100, removedState = 0)
        return try {
            client.post(PEOPLE_LIST_URL) {
                setBody(peopleListReq)
                contentType(ContentType.Application.Json)
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAg", e.toString())

            null
        }
    }
}