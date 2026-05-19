package com.behnamuix.appointment.di

import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentAddRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentDeleteRepo
import com.behnamuix.appointment.data.remote.remoteRepo.AppointmentListRepo
import com.behnamuix.appointment.data.remote.remoteRepo.PeopleListRepo
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
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            //set header
            install(DefaultRequest) {
                headers.append("accept", "*/*")
                headers.append("Content-Type", "application/json")
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60000L
                connectTimeoutMillis = 30000L
                socketTimeoutMillis = 60000L
            }

//            install(Logging) {
//                logger = object : Logger {
//                    override fun log(message: String) {
//                        // این پیام‌ها را در Logcat با فیلتر KtorDebug پیدا کنید
//                        Log.d("KtorDebug", message)
//                    }
//                }
//                level = LogLevel.ALL // حتماً روی ALL باشد تا Body را هم ببیند
//            }



        }
    }
}
val repositoryModule = module {
    single { AppointmentListRepo(get()) }
    single { AppointmentDeleteRepo(get()) }
    single { AppointmentAddRepo(get()) }
    single { PeopleListRepo(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { HomeViewModel() }
    viewModel { AppointmentListViewModel(get(), get()) }
    viewModel { AddAppointmentViewModel(get()) }
    viewModel { PeopleListViewModel(get()) }
    viewModel { AddPeopleViewModel() }
    viewModel { RemovedListViewModel() }

}
