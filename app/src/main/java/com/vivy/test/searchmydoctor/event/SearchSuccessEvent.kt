package com.vivy.test.searchmydoctor.event

import com.vivy.test.searchmydoctor.model.DoctorsList
import java.io.Serializable

class SearchSuccessEvent(private val searchResults: DoctorsList) : Serializable {

    fun getDoctors(): DoctorsList {
        return searchResults
    }
}