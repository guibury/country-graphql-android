package com.guilhermebury.countryinfo.helper.composition

import android.app.Activity
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.helper.navigation.ScreensNavigator
import com.guilhermebury.countryinfo.service.FetchCountry
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val layoutInflater get() = LayoutInflater.from(activity)
    val viewMvpFactory get() = ViewMvpFactory(layoutInflater)
    private val apolloClient get() = appCompositionRoot.apolloClient
    val fetchCountry: FetchCountry get() = FetchCountry(apolloClient)
}