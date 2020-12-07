package com.guilhermebury.countryinfo.dependencyinjection.presentation

import android.view.LayoutInflater
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.dependencyinjection.activity.ActivityComponent
import com.guilhermebury.countryinfo.service.FetchCountry
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val activityComponent: ActivityComponent) {

    @Provides
    fun layoutInflater() = activityComponent.layoutInflater()

    @Provides
    fun apolloClient() = activityComponent.apolloClient()

    @Provides
    fun screensNavigator() = activityComponent.screensNavigator()

    @Provides
    fun activity() = activityComponent.activity()

    @Provides
    fun fetchCountry(apolloClient: ApolloClient) = FetchCountry(apolloClient)

    @Provides
    fun viewMvpFactory(layoutInflater: LayoutInflater) = ViewMvpFactory(layoutInflater)
}