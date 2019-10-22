package com.guilhermebury.countryinfo.presenter

import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.service.GraphQLService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LatamPresenterImpl(val view: Contract.View) : Contract.LatamPresenter {

    private lateinit var country: CountryQuery.Country

    private lateinit var graphQlService: GraphQLService

    override fun getCountry(countryCode: String) {
        view.showLoading()

        graphQlService = GraphQLService()

        GlobalScope.launch (Dispatchers.Main) {
            country = graphQlService.fetchCountry(countryCode)

            view.onReceiveCountry(country)
            view.hideLoading()
        }
    }
}