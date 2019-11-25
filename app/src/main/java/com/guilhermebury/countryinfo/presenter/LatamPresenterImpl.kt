package com.guilhermebury.countryinfo.presenter

import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.service.ApolloRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class LatamPresenterImpl(val view: Contract.View) : Contract.LatamPresenter, KoinComponent {

    private lateinit var country: CountryQuery.Country

    private val apolloRequest: ApolloRequest by inject()

    override fun getCountry(countryCode: String) {
        view.showLoading()

        GlobalScope.launch (Dispatchers.Main) {
            country = apolloRequest.fetchCountry(countryCode)

            view.onReceiveCountry(country)
            view.hideLoading()
        }
    }
}