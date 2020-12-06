package com.guilhermebury.countryinfo.flags

import android.os.Bundle
import com.guilhermebury.countryinfo.BaseActivity
import com.guilhermebury.countryinfo.navigation.ScreensNavigator
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory

//Icons made by https://www.flaticon.com/authors/freepik from https://www.flaticon.com/
class FlagsActivity : BaseActivity(), FlagsViewMvp.Listener {

    lateinit var flagsViewMvp: FlagsViewMvp
    lateinit var screensNavigator: ScreensNavigator
    lateinit var viewMvpFactory: ViewMvpFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        flagsViewMvp =  viewMvpFactory.newFlagsViewMvp(null)

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