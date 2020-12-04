package com.guilhermebury.countryinfo.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.service.FetchCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class DetailsActivity : AppCompatActivity(), DetailsViewMvp.Listener {

    private lateinit var detailsViewMvp: DetailsViewMvp
    private lateinit var countryCode: String
    private lateinit var country: CountryQuery.Country

    private val fetchCountry: FetchCountry by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewMvp = DetailsViewMvp(LayoutInflater.from(this), null)

        countryCode = intent.extras!!.getString(EXTRA_COUNTRY_CODE)!!

        setContentView(detailsViewMvp.rootView)
    }

    override fun onStart() {
        super.onStart()
        detailsViewMvp.registerListener(this)
        fetchCountryDetails()
    }

    private fun fetchCountryDetails() {
        detailsViewMvp.showLoading()

        GlobalScope.launch (Dispatchers.Main) {
            country = fetchCountry.fetchCountry(countryCode)

            detailsViewMvp.onReceiveCountry(country)
            detailsViewMvp.hideLoading()
        }
    }

    override fun onStop() {
        super.onStop()
        detailsViewMvp.unregisterListener(this)
    }

    companion object {
        const val EXTRA_COUNTRY_CODE = "EXTRA_COUNTRY_CODE"
        fun start(context: Context, countryCode: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(EXTRA_COUNTRY_CODE, countryCode)
            context.startActivity(intent)
        }
    }
}