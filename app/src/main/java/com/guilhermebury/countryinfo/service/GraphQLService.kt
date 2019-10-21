package com.guilhermebury.countryinfo.service

import android.content.Intent
import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.CountryQuery.*
import okhttp3.OkHttpClient
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.presenter.LatamPresenterImpl
import org.jetbrains.annotations.NotNull

class GraphQLService() {

    private val TAG = "gbury.countryinfo"
    private val BASE_URL = "https://countries.trevorblades.com/"
    private val okHttpClient = OkHttpClient.Builder().build()
    private val apolloClient = ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build()
    private lateinit var data: Response<Data>

    companion object {
        lateinit var country: Country
    }


    private val mock = "{\n" +
            "  \"data\": {\n" +
            "    \"country\": {\n" +
            "      \"name\": \"Argentina\",\n" +
            "      \"native\": \"Argentina\",\n" +
            "      \"emoji\": \"\uD83C\uDDE6\uD83C\uDDF7\",\n" +
            "      \"continent\": {\n" +
            "        \"name\": \"South America\"\n" +
            "      },\n" +
            "      \"languages\": [\n" +
            "        {\n" +
            "          \"name\": \"Spanish\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Guarani\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}"


    //TODO observable
    fun fetchCountry(countryCode: String) {

        apolloClient.query(CountryQuery.builder().code(countryCode).build())
            .enqueue(object : ApolloCall.Callback<Data>() {
                override fun onResponse(@NotNull response: Response<Data>) {
                    data = response
                    Log.d(TAG, data.toString())

                    val dataResponse = data.data()?.country()
                    country = Country(dataResponse!!.__typename(),
                        dataResponse.name(),
                        dataResponse.native_(),
                        dataResponse.emoji(),
                        dataResponse.continent(),
                        dataResponse.languages())
                    //view.onReceiveCountry(toCountry(data))
                }

                override fun onFailure(e: ApolloException) {
                    Log.e(TAG, e.localizedMessage)
                }
            })
    }

//    fun toCountry(dataResponse: Response<Data>) {
//        val data = dataResponse.data()?.country()
//
//        country = Country(data!!.__typename(),
//                data.name(),
//                data.native_(),
//                data.emoji(),
//                data.continent(),
//                data.languages())
//    }
}