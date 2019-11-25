package com.guilhermebury.countryinfo

import android.app.Application
import com.guilhermebury.countryinfo.dependencyinjection.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(listOf(applicationModule))
        }
    }
}