package com.guilhermebury.countryinfo.presenter

import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.service.GraphQLService

class LatamPresenterImpl(val view: Contract.View) : Contract.LatamPresenter {

    private lateinit var graphQlService: GraphQLService

    override fun getCountry(countryCode: String) {
        view.showLoading()

        graphQlService = GraphQLService(view)
        graphQlService.fetchCountry(countryCode)

        //view.onReceiveCountry(GraphQLService.country)
        //view.hideLoading()
    }
}