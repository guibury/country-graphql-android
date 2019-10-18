package com.guilhermebury.countryinfo.contract

import com.guilhermebury.countryinfo.CountryQuery.*

interface Contract {

    interface View {
        fun showLoading()
        fun hideLoading()
        fun showError(errorMessage: String)
        fun onReceiveCountry(country: Country)
    }

    interface Presenter {
        fun getCountry(countryCode: String)
    }

    interface Listener {
        fun onFlagClicked(countryCode: String)
    }
}