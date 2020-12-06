package com.guilhermebury.countryinfo.dependencyinjection

import com.guilhermebury.countryinfo.service.FetchCountry
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val apolloClient get() = activityCompositionRoot.apolloClient

    val screensNavigator get() = activityCompositionRoot.screensNavigator
    val fetchCountry get() = FetchCountry(apolloClient)
    val viewMvpFactory get() = ViewMvpFactory(layoutInflater)
}