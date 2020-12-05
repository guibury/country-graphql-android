package com.guilhermebury.countryinfo

import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.helper.composition.ActivityCompositionRoot
import com.guilhermebury.countryinfo.helper.composition.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {
    private val appCompositionRoot get() =
        (application as BaseApplication).appCompositionRoot

    private val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    protected val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }
}