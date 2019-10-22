package com.guilhermebury.countryinfo.service

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.CountryQuery.*
import okhttp3.OkHttpClient
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import org.jetbrains.annotations.NotNull
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GraphQLService {

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

    suspend fun fetchCountry(countryCode: String) : Country {

        return suspendCoroutine { continuation ->
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

                        continuation.resume(country)
                    }

                    override fun onFailure(e: ApolloException) {
                        Log.e(TAG, e.localizedMessage)
                    }
                })
        }

    }
}