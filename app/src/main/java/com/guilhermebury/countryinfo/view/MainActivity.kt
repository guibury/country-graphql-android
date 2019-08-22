package com.guilhermebury.countryinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.guilhermebury.countryinfo.CountryQuery.*
import com.guilhermebury.countryinfo.R
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.helper.Countries
import com.guilhermebury.countryinfo.presenter.PresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

//Icons made by https://www.flaticon.com/authors/freepik from https://www.flaticon.com/
class MainActivity : AppCompatActivity(), Contract.View {

    private lateinit var presenter: Contract.Presenter
    private lateinit var country: Country

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = PresenterImpl(this)
    }

    fun flagClicked(view: View?) {
        when(view) {
            argentinaButton -> presenter.getCountry(Countries.ARGENTINA.code)
            brazilButton -> presenter.getCountry(Countries.BRAZIL.code)
            chileButton -> presenter.getCountry(Countries.CHILE.code)
            paraguayButton -> presenter.getCountry(Countries.PARAGUAY.code)
            peruButton -> presenter.getCountry(Countries.PERU.code)
            uruguayButton -> presenter.getCountry(Countries.URUGUAY.code)
        }
    }

    override fun showLoading() {
        Toast.makeText(this, "Too lazy to make a loading...", Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onReceiveCountry(country: Country) {
        this.country = country

        runOnUiThread {
            countryNameText.visibility = View.VISIBLE
            countryNativeText.visibility = View.VISIBLE
            countryContinentText.visibility = View.VISIBLE
            countryEmojiText.visibility = View.VISIBLE
            countryLanguageText.visibility = View.VISIBLE

            countryNameText.text = country.name()
            countryNativeText.text = country.native_()
            countryContinentText.text = country.continent()?.name()
            countryEmojiText.text = country.emoji()
            countryLanguageText.text = country.languages()?.get(0)?.name()
        }
    }
}