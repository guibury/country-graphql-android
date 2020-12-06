package com.guilhermebury.countryinfo

import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.dependencyinjection.ActivityCompositionRoot
import com.guilhermebury.countryinfo.dependencyinjection.DaggerPresentationComponent
import com.guilhermebury.countryinfo.dependencyinjection.Injector
import com.guilhermebury.countryinfo.dependencyinjection.PresentationModule

open class BaseActivity : AppCompatActivity() {
    private val appCompositionRoot get() =
        (application as BaseApplication).appCompositionRoot

    private val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val presentationComponent by lazy {
        DaggerPresentationComponent.builder().presentationModule(
            PresentationModule(activityCompositionRoot))
            .build()
    }
    protected val injector get() = Injector(presentationComponent)
}