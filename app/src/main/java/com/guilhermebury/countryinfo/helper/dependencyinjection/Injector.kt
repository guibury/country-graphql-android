package com.guilhermebury.countryinfo.helper.dependencyinjection

import com.guilhermebury.countryinfo.details.DetailsActivity
import com.guilhermebury.countryinfo.flags.FlagsActivity

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(activity: DetailsActivity) {
        activity.viewMvpFactory = compositionRoot.viewMvpFactory
        activity.fetchCountry = compositionRoot.fetchCountry
    }

    fun inject(activity: FlagsActivity) {
        activity.viewMvpFactory = compositionRoot.viewMvpFactory
        activity.screensNavigator = compositionRoot.screensNavigator
    }
}