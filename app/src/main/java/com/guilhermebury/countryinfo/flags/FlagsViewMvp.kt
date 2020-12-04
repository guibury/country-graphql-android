package com.guilhermebury.countryinfo.flags

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.guilhermebury.countryinfo.BaseViewMvc
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.R
import com.guilhermebury.countryinfo.helper.Countries
import kotlinx.android.synthetic.main.flags_view_mvp.view.*

/*
* 1 Constructor
* 2 Inflate view
* 3 AttachToRoot false - Error: android view already has a parent
* 4 Activity aware of MVC view
* 5 Migrate all UI logic from the Activity
* 6 Notification mechanism from view to activity (Observable)
* 7 Send notifications when flag is clicked
* 8 Register activity as listener of the view
* */

class FlagsViewMvp(
    inflater: LayoutInflater,
    parent: ViewGroup?
): BaseViewMvc<FlagsViewMvp.Listener>(
    inflater,
    parent,
    R.layout.flags_view_mvp) {

    interface Listener {
        fun onFlagClicked(countryCode: String)
    }

    init {
        rootView.argentinaButton.setOnClickListener {onFlagClicked(Countries.ARGENTINA.code)}
        rootView.brazilButton.setOnClickListener {onFlagClicked(Countries.BRAZIL.code)}
        rootView.chileButton.setOnClickListener {onFlagClicked(Countries.CHILE.code)}
        rootView.paraguayButton.setOnClickListener {onFlagClicked(Countries.PARAGUAY.code)}
        rootView.peruButton.setOnClickListener {onFlagClicked(Countries.PERU.code)}
        rootView.uruguayButton.setOnClickListener {onFlagClicked(Countries.URUGUAY.code)}
        rootView.usaButton.setOnClickListener {onFlagClicked(Countries.USA.code)}
        rootView.canadaButton.setOnClickListener {onFlagClicked(Countries.CANADA.code)}
        rootView.mexicoButton.setOnClickListener {onFlagClicked(Countries.MEXICO.code)}
    }

    fun onFlagClicked(countryCode: String) {
        listeners(countryCode)
    }

    private fun listeners(countryCode: String) {
        for (listener in listeners) {
            listener.onFlagClicked(countryCode)
        }
    }

    fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}