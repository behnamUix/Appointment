package com.behnamuix.appointment.di

import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.viewModel.appointment.AddAppointmentViewModel
import com.behnamuix.appointment.viewModel.people.AddPeopleViewModel
import com.behnamuix.appointment.viewModel.appointment.AppointmentListViewModel
import com.behnamuix.appointment.viewModel.HomeViewModel
import com.behnamuix.appointment.viewModel.people.PeopleListViewModel
import com.behnamuix.appointment.viewModel.removed.RemovedListViewModel
import com.behnamuix.appointment.viewModel.SplashViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(CIO) {
            //json parser hast
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }
            //set header
            install(DefaultRequest) {
                headers.append("accept", "*/*")
                headers.append("Content-Type", "application/json")
            }
        }
    }
}
val repositoryModule = module {
    single { AppointmentListRepo(get()) }
    single { AppointmentDeleteRepo(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AppointmentListViewModel(get(), get()) }
    viewModel { AddAppointmentViewModel() }
    viewModel { PeopleListViewModel() }
    viewModel { AddPeopleViewModel() }
    viewModel { RemovedListViewModel() }

}
