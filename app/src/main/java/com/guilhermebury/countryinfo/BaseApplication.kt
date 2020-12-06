package com.guilhermebury.countryinfo

import android.app.Application
import com.guilhermebury.countryinfo.helper.dependencyinjection.AppCompositionRoot

class BaseApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        super.onCreate()
        appCompositionRoot = AppCompositionRoot()
    }
}