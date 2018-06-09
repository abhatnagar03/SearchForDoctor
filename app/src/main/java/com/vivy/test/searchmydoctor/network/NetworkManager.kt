package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.model.LoginToken
import retrofit.Callback

interface NetworkManager {
    fun login(username: String, password: String, serverTokenResponse: Callback<LoginToken>)
    fun searchDoctorByName(docName: String, latitude: Float, longitude: Float, searchResponse: Callback<DoctorsList>)
    fun searchAllDoctors(latitude: Float, longitude: Float, searchResponse: Callback<DoctorsList>)
}