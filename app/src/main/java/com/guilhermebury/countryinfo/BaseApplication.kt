package com.guilhermebury.countryinfo

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.service.FetchCountry
import okhttp3.OkHttpClient

class BaseApplication : Application() {

    private val baseUrl = "https://countries.trevorblades.com/"
    private val okHttpClient = OkHttpClient.Builder().build()
    private val apolloClient: ApolloClient = ApolloClient.builder().serverUrl(baseUrl).okHttpClient(okHttpClient).build()
    val fetchCountry: FetchCountry get() = FetchCountry(apolloClient) //get() to create a new instance on every call

    override fun onCreate() {
        super.onCreate()
    }
}