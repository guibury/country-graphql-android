package com.guilhermebury.countryinfo.flags

import android.os.Bundle
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.BaseActivity
import com.guilhermebury.countryinfo.helper.navigation.ScreensNavigator

//Icons made by https://www.flaticon.com/authors/freepik from https://www.flaticon.com/
class FlagsActivity : BaseActivity(), FlagsViewMvp.Listener {

    private lateinit var flagsViewMvp: FlagsViewMvp
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flagsViewMvp = FlagsViewMvp(LayoutInflater.from(this), null) //Activity doesn't have a root view
        screensNavigator = compositionRoot.screensNavigator

        setContentView(flagsViewMvp.rootView)
    }

    override fun onStart() {
        super.onStart()
        flagsViewMvp.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        flagsViewMvp.unregisterListener(this)
    }

    override fun onFlagClicked(countryCode: String) {
        screensNavigator.toCountryDetails(countryCode)
    }
}