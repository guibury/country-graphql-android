package com.guilhermebury.countryinfo.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.guilhermebury.countryinfo.BaseViewMvp
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.R
import kotlinx.android.synthetic.main.details_view_mvp.view.*

class DetailsViewMvp(inflater: LayoutInflater, parent: ViewGroup?
): BaseViewMvp<DetailsViewMvp.Listener>(
inflater,
parent,
R.layout.details_view_mvp){

    interface Listener

    fun showLoading() {
        rootView.progress.visibility = View.VISIBLE
    }

    fun hideLoading() {
        rootView.progress.visibility = View.GONE
    }

    fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    fun onReceiveCountry(country: CountryQuery.Country) {
        rootView.countryNameText.text = country.name()
        rootView.countryNativeText.text = country.native_()
        rootView.countryContinentText.text = country.continent().name()
        rootView.countryEmojiText.text = country.emoji()
        rootView.countryLanguageText.text = country.languages().firstOrNull()?.name()
    }
}