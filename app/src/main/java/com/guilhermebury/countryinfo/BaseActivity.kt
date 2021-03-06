package com.guilhermebury.countryinfo

import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.dependencyinjection.ActivityCompositionRoot
import com.guilhermebury.countryinfo.dependencyinjection.Injector
import com.guilhermebury.countryinfo.dependencyinjection.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {
    private val appCompositionRoot get() =
        (application as BaseApplication).appCompositionRoot

    private val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}