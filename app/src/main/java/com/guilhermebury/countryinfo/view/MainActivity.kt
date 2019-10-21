package com.guilhermebury.countryinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.presenter.LatamPresenterImpl

//Icons made by https://www.flaticon.com/authors/freepik from https://www.flaticon.com/
class MainActivity : AppCompatActivity(), Contract.Listener {

    override fun onFlagClicked(countryCode: String) {
        presenter.getCountry(countryCode)
    }

    private lateinit var presenter: Contract.Presenter
    //private lateinit var country: Country

    private lateinit var latamViewMvpImpl: LatamViewMvpImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        latamViewMvpImpl = LatamViewMvpImpl(LayoutInflater.from(this), null) //Activity doesn't have a root view
        latamViewMvpImpl.registerListener(this)

        presenter = LatamPresenterImpl(latamViewMvpImpl)

        setContentView(latamViewMvpImpl.rootView)
    }
}