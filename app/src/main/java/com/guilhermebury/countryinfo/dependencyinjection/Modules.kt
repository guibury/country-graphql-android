package com.guilhermebury.countryinfo.dependencyinjection

import com.guilhermebury.countryinfo.service.FetchCountry
import org.koin.dsl.module

val applicationModule = module {
    factory { FetchCountry() }
}