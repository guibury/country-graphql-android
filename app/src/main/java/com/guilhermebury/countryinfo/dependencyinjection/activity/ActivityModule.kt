package com.guilhermebury.countryinfo.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.dependencyinjection.app.AppComponent
import com.guilhermebury.countryinfo.navigation.ScreensNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity,
    private val appComponent: AppComponent
) {

    private val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    @Provides
    fun activity() = activity

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun screensNavigator(activity: AppCompatActivity) = screensNavigator

    @Provides
    fun layoutInflater(): LayoutInflater = LayoutInflater.from(activity)

    @Provides
    fun apolloClient() = appComponent.apolloClient()
}