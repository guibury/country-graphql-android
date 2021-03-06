package com.guilhermebury.countryinfo.service

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.CountryQuery.*
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import org.jetbrains.annotations.NotNull
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FetchCountry(private val apolloClient: ApolloClient) {

    private val tag = "gbury.countryinfo"

    suspend fun fetchCountry(countryCode: String) : Country {

        return suspendCoroutine { continuation ->
            apolloClient.query(builder().code(countryCode).build())
                .enqueue(object : ApolloCall.Callback<Data>() {
                    override fun onResponse(@NotNull response: Response<Data>) {
                        Log.d(tag, response.data().toString())
                        continuation.resume(ParseCountry().parseCountry(response))
                    }

                    override fun onFailure(e: ApolloException) {
                        Log.e(tag, e.localizedMessage ?: "Unknown error")
                    }
                })
        }
    }
}

class ParseCountry {

    fun parseCountry(response: Response<Data>) : Country {
        val dataResponse = response.data()?.country()

        return Country(dataResponse!!.__typename(),
            dataResponse.name(),
            dataResponse.native_(),
            dataResponse.emoji(),
            dataResponse.continent(),
            dataResponse.languages())
    }
}