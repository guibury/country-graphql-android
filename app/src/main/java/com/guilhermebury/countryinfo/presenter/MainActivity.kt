package com.guilhermebury.countryinfo.presenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.view.LatamViewMvp

//Icons made by https://www.flaticon.com/authors/freepik from https://www.flaticon.com/
class MainActivity : AppCompatActivity(), Contract.Listener {

    private lateinit var latamPresenter: Contract.LatamPresenter
    private lateinit var latamViewMvp: LatamViewMvp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        latamViewMvp = LatamViewMvp(LayoutInflater.from(this), null) //Activity doesn't have a root view
        latamViewMvp.registerListener(this)

        latamPresenter = LatamPresenterImpl(latamViewMvp)

        setContentView(latamViewMvp.getRootView())
    }

    override fun onFlagClicked(countryCode: String) {
        latamPresenter.getCountry(countryCode)
    }
}