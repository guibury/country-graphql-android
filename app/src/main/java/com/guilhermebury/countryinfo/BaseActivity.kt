package com.guilhermebury.countryinfo

import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.helper.composition.ActivityCompositionRoot

open class BaseActivity : AppCompatActivity() {
    private val appCompositionRoot get() =
        (application as BaseApplication).appCompositionRoot

    val compositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }
}