package com.guilhermebury.countryinfo

import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.dependencyinjection.*
import com.guilhermebury.countryinfo.dependencyinjection.activity.ActivityModule
import com.guilhermebury.countryinfo.dependencyinjection.activity.DaggerActivityComponent
import com.guilhermebury.countryinfo.dependencyinjection.presentation.DaggerPresentationComponent
import com.guilhermebury.countryinfo.dependencyinjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private val appCompositionRoot get() =
        (application as BaseApplication).appComponent

    private val activityComponent by lazy {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this, appCompositionRoot))
                .build()
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder()
            .presentationModule(PresentationModule(activityComponent))
            .build()
    }
    protected val injector get() = Injector(presentationComponent)
}