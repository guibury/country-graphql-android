package com.guilhermebury.countryinfo.helper.composition

import android.app.Activity
import com.guilhermebury.countryinfo.helper.navigation.ScreensNavigator
import com.guilhermebury.countryinfo.service.FetchCountry

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot
) {

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    private val apolloClient get() = appCompositionRoot.apolloClient
    val fetchCountry: FetchCountry get() = FetchCountry(apolloClient)
}