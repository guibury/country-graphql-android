package com.guilhermebury.countryinfo.dependencyinjection

import com.guilhermebury.countryinfo.details.DetailsActivity
import com.guilhermebury.countryinfo.flags.FlagsActivity

class Injector(private val component: PresentationComponent) {

    fun inject(activity: DetailsActivity) {
        activity.viewMvpFactory = component.viewMvpFactory()
        activity.fetchCountry = component.fetchCountry()
    }

    fun inject(activity: FlagsActivity) {
        activity.viewMvpFactory = component.viewMvpFactory()
        activity.screensNavigator = component.screensNavigator()
    }
}