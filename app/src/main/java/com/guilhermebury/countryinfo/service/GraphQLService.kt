package com.guilhermebury.countryinfo.service

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.CountryQuery.*
import okhttp3.OkHttpClient
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.guilhermebury.countryinfo.contract.Contract
import org.jetbrains.annotations.NotNull

class GraphQLService(val view: Contract.View) {

    private val TAG = "gbury.countryinfo"
    private val BASE_URL = "https://countries.trevorblades.com/"
    private val okHttpClient = OkHttpClient.Builder().build()
    private val apolloClient = ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build()
    private lateinit var data: Response<Data>

    fun fetchCountry(countryCode: String) {
        view.showLoading()

        apolloClient.query(CountryQuery.builder().code(countryCode).build())
            .enqueue(object : ApolloCall.Callback<Data>() {
                override fun onResponse(@NotNull response: Response<Data>) {
                    data = response
                    Log.d(TAG, response.toString())

                    view.onReceiveCountry(toCountry(data))
                }

                override fun onFailure(e: ApolloException) {
                    Log.e(TAG, e.localizedMessage)
                    view.showError(e.localizedMessage)
                }
            })
    }

    fun toCountry(dataResponse: Response<Data>) : Country {
        val data = dataResponse.data()?.country()

        return Country(data!!.__typename(),
                data.name(),
                data.native_(),
                data.emoji(),
                data.continent(),
                data.languages())
    }
}