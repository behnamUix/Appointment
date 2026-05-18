package com.behnamuix.appointment.base

import android.app.Application
import com.behnamuix.appointment.di.networkModule
import com.behnamuix.appointment.di.repositoryModule
import com.behnamuix.appointment.di.viewModelModule
import com.behnamuix.appointment.viewModel.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule, repositoryModule, networkModule
                )
            )
        }
    }
}