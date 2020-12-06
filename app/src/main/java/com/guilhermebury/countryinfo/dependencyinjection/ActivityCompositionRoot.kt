package com.guilhermebury.countryinfo.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.navigation.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
    val apolloClient get() = appCompositionRoot.apolloClient
}