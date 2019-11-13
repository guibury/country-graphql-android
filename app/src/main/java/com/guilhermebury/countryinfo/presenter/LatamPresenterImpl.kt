package com.guilhermebury.countryinfo.presenter

import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.service.ApolloRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LatamPresenterImpl(val view: Contract.View) : Contract.LatamPresenter {

    private lateinit var country: CountryQuery.Country

    private lateinit var apolloRequest: ApolloRequest

    override fun getCountry(countryCode: String) {
        view.showLoading()

        apolloRequest = ApolloRequest()

        GlobalScope.launch (Dispatchers.Main) {
            country = apolloRequest.fetchCountry(countryCode)

            view.onReceiveCountry(country)
            view.hideLoading()
        }
    }
}