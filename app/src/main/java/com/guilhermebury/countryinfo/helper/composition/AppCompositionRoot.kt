package com.guilhermebury.countryinfo.helper.composition

import androidx.annotation.UiThread
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.helper.Constants.BASE_URL
import com.guilhermebury.countryinfo.service.FetchCountry
import okhttp3.OkHttpClient

@UiThread
class AppCompositionRoot {
    //Lazy to don't add initialization time on app startup
    private val okHttpClient : OkHttpClient by lazy {  OkHttpClient.Builder().build() }
    private val apolloClient: ApolloClient by lazy {
        ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build()
    }
    //get() to create a new instance on every call
    val fetchCountry: FetchCountry get() = FetchCountry(apolloClient)
}