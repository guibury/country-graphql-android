package com.guilhermebury.countryinfo.service

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.CountryQuery.*
import okhttp3.OkHttpClient
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import org.jetbrains.annotations.NotNull
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GraphQLService {

    private val tag = "gbury.countryinfo"
    private val baseUrl = "https://countries.trevorblades.com/"
    private val okHttpClient = OkHttpClient.Builder().build()
    private val apolloClient = ApolloClient.builder().serverUrl(baseUrl).okHttpClient(okHttpClient).build()
    private lateinit var data: Response<Data>

    companion object {
        lateinit var country: Country
    }

    suspend fun fetchCountry(countryCode: String) : Country {

        return suspendCoroutine { continuation ->
            apolloClient.query(builder().code(countryCode).build())
                .enqueue(object : ApolloCall.Callback<Data>() {
                    override fun onResponse(@NotNull response: Response<Data>) {

                        data = response
                        Log.d(tag, data.toString())
                        val dataResponse = data.data()?.country()
                        country = Country(dataResponse!!.__typename(),
                            dataResponse.name(),
                            dataResponse.native_(),
                            dataResponse.emoji(),
                            dataResponse.continent(),
                            dataResponse.languages())

                        continuation.resume(country)
                    }

                    override fun onFailure(e: ApolloException) {
                        Log.e(tag, e.localizedMessage ?: "Unknown error")
                    }
                })
        }
    }
}