package com.guilhermebury.countryinfo.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.apollographql.apollo.ApolloClient
import com.guilhermebury.countryinfo.navigation.ScreensNavigator
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun activity(): AppCompatActivity
    fun layoutInflater(): LayoutInflater
    fun apolloClient(): ApolloClient
    fun screensNavigator(): ScreensNavigator
}