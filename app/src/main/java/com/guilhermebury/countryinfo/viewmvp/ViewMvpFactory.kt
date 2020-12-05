package com.guilhermebury.countryinfo.viewmvp

import android.view.LayoutInflater
import android.view.ViewGroup
import com.guilhermebury.countryinfo.details.DetailsViewMvp
import com.guilhermebury.countryinfo.flags.FlagsViewMvp

class ViewMvpFactory(private val layoutInflater: LayoutInflater) {

    fun newDetailsViewMvp(parent: ViewGroup?): DetailsViewMvp {
        return DetailsViewMvp(layoutInflater, parent)
    }

    fun newFlagsViewMvp(parent: ViewGroup?): FlagsViewMvp {
        return FlagsViewMvp(layoutInflater, parent)
    }
}