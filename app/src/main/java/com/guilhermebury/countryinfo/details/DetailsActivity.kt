package com.guilhermebury.countryinfo.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.guilhermebury.countryinfo.BaseActivity
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.service.FetchCountry
import com.guilhermebury.countryinfo.viewmvp.ViewMvpFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsActivity : BaseActivity(), DetailsViewMvp.Listener {

    lateinit var detailsViewMvp: DetailsViewMvp
    lateinit var countryCode: String
    lateinit var country: CountryQuery.Country
    lateinit var fetchCountry: FetchCountry
    lateinit var viewMvpFactory: ViewMvpFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        detailsViewMvp = viewMvpFactory.newDetailsViewMvp(null)
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