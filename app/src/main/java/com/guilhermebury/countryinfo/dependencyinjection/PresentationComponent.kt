package com.guilhermebury.countryinfo.dependencyinjection

import com.guilhermebury.countryinfo.navigation.ScreensNavigator
import com.guilhermebury.countryinfo.service.FetchCountry
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun screensNavigator(): ScreensNavigator

    fun fetchCountry(): FetchCountry

    fun viewMvpFactory(): ViewMvpFactory
}