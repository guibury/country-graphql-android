package com.guilhermebury.countryinfo.dependencyinjection

import android.view.LayoutInflater
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.service.FetchCountry
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityCompositionRoot: ActivityCompositionRoot) {

    @Provides
    fun layoutInflater() = activityCompositionRoot.layoutInflater

    @Provides
    fun apolloClient() = activityCompositionRoot.apolloClient

    @Provides
    fun screensNavigator() = activityCompositionRoot.screensNavigator

    @Provides
    fun fetchCountry(apolloClient: ApolloClient) = FetchCountry(apolloClient)

    @Provides
    fun viewMvpFactory(layoutInflater: LayoutInflater) = ViewMvpFactory(layoutInflater)
}