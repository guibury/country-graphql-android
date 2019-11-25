package com.guilhermebury.countryinfo.dependencyinjection

import com.guilhermebury.countryinfo.service.ApolloRequest
import org.koin.dsl.module

val applicationModule = module {
    factory { ApolloRequest() }
}