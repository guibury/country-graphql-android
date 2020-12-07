package com.guilhermebury.countryinfo.dependencyinjection.app

import android.app.Application
import com.apollographql.apollo.ApolloClient
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application
    fun apolloClient(): ApolloClient
}