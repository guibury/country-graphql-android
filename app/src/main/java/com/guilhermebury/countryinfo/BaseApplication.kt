package com.guilhermebury.countryinfo

import android.app.Application
import com.guilhermebury.countryinfo.dependencyinjection.app.AppModule
import com.guilhermebury.countryinfo.dependencyinjection.DaggerAppComponent

class BaseApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}