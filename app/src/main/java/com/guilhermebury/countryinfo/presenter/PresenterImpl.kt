package com.guilhermebury.countryinfo.presenter

import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.service.GraphQLService

class PresenterImpl(private val view: Contract.View) : Contract.Presenter {

    private lateinit var graphQlService: GraphQLService

    override fun getCountry(countryCode: String) {
        graphQlService = GraphQLService(view)
        graphQlService.fetchCountry(countryCode)
    }
}