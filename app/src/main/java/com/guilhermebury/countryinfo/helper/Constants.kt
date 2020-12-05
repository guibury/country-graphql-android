package com.guilhermebury.countryinfo.helper

enum class Countries(val code: String) {
    ARGENTINA("AR"),
    BRAZIL ("BR"),
    CHILE("CL"),
    PARAGUAY("PY"),
    PERU("PE"),
    URUGUAY("UY"),
    USA("US"),
    CANADA("CA"),
    MEXICO("MX")
}

object Constants {
    const val BASE_URL = "https://countries.trevorblades.com/"
}