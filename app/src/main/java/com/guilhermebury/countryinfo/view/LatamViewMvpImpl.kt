package com.guilhermebury.countryinfo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.guilhermebury.countryinfo.CountryQuery
import com.guilhermebury.countryinfo.R
import com.guilhermebury.countryinfo.contract.Contract
import com.guilhermebury.countryinfo.helper.Countries
import kotlinx.android.synthetic.main.latam_view_mvp.view.*

/*
* 1 Constructor
* 2 Inflate view
* 3 AttachToRoot false - Error: android view already has a parent
* 4 Activity aware of MVC view
* 5 Migrate all UI logic from the Activity
* 6 Notification mechanism from view to activity (Observable)
* 7 Send notifications when flag is clicked
* 8 Register activity as listener of the view
* */
class LatamViewMvpImpl(inflater: LayoutInflater, parent: ViewGroup?): Contract.View {

    private val listeners = ArrayList<Contract.Listener>(1)
    private var countryCode = ""
    val rootView: View = inflater.inflate(R.layout.latam_view_mvp, parent, false)

    init {
        rootView.argentinaButton.setOnClickListener {onFlagClicked(rootView.argentinaButton)}
//        rootView.brazilButton.setOnClickListener {onClick()}
//        rootView.chileButton.setOnClickListener {onClick()}
//        rootView.paraguayButton.setOnClickListener {onClick()}
//        rootView.peruButton.setOnClickListener {onClick()}
//        rootView.uruguayButton.setOnClickListener {onClick()}

    }

    private fun listeners() {
        for (listener in listeners) {
            listener.onFlagClicked(countryCode)
        }
    }

    override fun registerListener(listener: Contract.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Contract.Listener) {
        listeners.remove(listener)
    }

    private fun context() : Context {
        return rootView.context
    }

    override fun onFlagClicked(view: View?) {

        when(view) {
            rootView.argentinaButton -> countryCode = Countries.ARGENTINA.code
            rootView.brazilButton -> countryCode = Countries.BRAZIL.code
            rootView.chileButton -> countryCode = Countries.CHILE.code
            rootView.paraguayButton -> countryCode = Countries.PARAGUAY.code
            rootView.peruButton -> countryCode = Countries.PERU.code
            rootView.uruguayButton -> countryCode = Countries.URUGUAY.code
        }
        listeners()
    }

    override fun showLoading() {
        Toast.makeText(context(), "Too lazy to make a loading...", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onReceiveCountry(country: CountryQuery.Country) {

        rootView.countryNameText.visibility = View.VISIBLE
        rootView.countryNativeText.visibility = View.VISIBLE
        rootView.countryContinentText.visibility = View.VISIBLE
        rootView.countryEmojiText.visibility = View.VISIBLE
        rootView.countryLanguageText.visibility = View.VISIBLE

        rootView.countryNameText.text = country.name()
        rootView.countryNativeText.text = country.native_()
        rootView.countryContinentText.text = country.continent()?.name()
        rootView.countryEmojiText.text = country.emoji()
        rootView.countryLanguageText.text = country.languages()?.get(0)?.name()
    }
}