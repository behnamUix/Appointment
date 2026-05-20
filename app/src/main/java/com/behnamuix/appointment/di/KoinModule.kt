package com.behnamuix.appointment.di

import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentAddRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentRestoreRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleAddRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleListRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleRestoreRepo
import com.behnamuix.appointment.viewModel.HomeViewModel
import com.behnamuix.appointment.viewModel.SplashViewModel
import com.behnamuix.appointment.viewModel.appointment.AddAppointmentViewModel
import com.behnamuix.appointment.viewModel.appointment.AppointmentListViewModel
import com.behnamuix.appointment.viewModel.people.AddPeopleViewModel
import com.behnamuix.appointment.viewModel.people.PeopleListViewModel
import com.behnamuix.appointment.viewModel.removed.RemovedListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(CIO) {

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(DefaultRequest) {
                header("Accept", "application/json")
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 60000L
                connectTimeoutMillis = 30000L
                socketTimeoutMillis = 60000L
            }
        }
    }
}
val repositoryModule = module {
    single { AppointmentListRepo(get()) }
    single { AppointmentDeleteRepo(get()) }
    single { AppointmentAddRepo(get()) }
    single { AppointmentRestoreRepo(get()) }
    single { PeopleListRepo(get()) }
    single { PeopleAddRepo(get()) }
    single { PeopleDeleteRepo(get()) }
    single { PeopleRestoreRepo(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AppointmentListViewModel(get(), get()) }
    viewModel { AddAppointmentViewModel(get()) }
    viewModel { PeopleListViewModel(get(), get()) }
    viewModel { AddPeopleViewModel(get()) }
    viewModel { RemovedListViewModel(
        get(),
        get(),
        get(),
        get()
    ) }

}
