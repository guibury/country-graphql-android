package com.guilhermebury.countryinfo.navigation

import android.app.Activity
import com.guilhermebury.countryinfo.details.DetailsActivity

class ScreensNavigator(private val activity: Activity) {

    fun toCountryDetails(countryCode: String) {
        DetailsActivity.start(activity, countryCode)
    }
}