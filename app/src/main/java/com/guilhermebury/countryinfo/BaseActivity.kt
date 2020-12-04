package com.guilhermebury.countryinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.guilhermebury.countryinfo.helper.composition.AppCompositionRoot

open class BaseActivity : AppCompatActivity() {
    lateinit var compositionRoot: AppCompositionRoot

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositionRoot = (application as BaseApplication).appCompositionRoot
    }
}