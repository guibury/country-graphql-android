package com.guilhermebury.countryinfo.flags

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.helper.ScreensNavigator

//Icons made by https://www.flaticon.com/authors/freepik from https://www.flaticon.com/
class FlagsActivity : AppCompatActivity(), FlagsViewMvp.Listener {

    private lateinit var flagsViewMvp: FlagsViewMvp
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flagsViewMvp = FlagsViewMvp(LayoutInflater.from(this), null) //Activity doesn't have a root view
        screensNavigator = ScreensNavigator(this)

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