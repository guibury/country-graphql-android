package com.guilhermebury.countryinfo.dependencyinjection.app

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.helper.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class AppModule(private val application: Application) {

    private val okHttpClient : OkHttpClient by lazy {  OkHttpClient.Builder().build() }

    private val apolloClient: ApolloClient by lazy {
        ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build()
    }

    @Provides
    fun application() = application

    @Provides
    fun apolloClient() = apolloClient
}