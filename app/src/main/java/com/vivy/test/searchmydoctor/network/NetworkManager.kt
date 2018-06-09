package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.model.LoginToken
import io.reactivex.disposables.Disposable
import retrofit2.Callback

interface NetworkManager {
    fun login(username: String, password: String): Disposable
    fun searchDoctorByName(docName: String, latitude: Float, longitude: Float): Disposable
    fun searchAllDoctors(latitude: Float, longitude: Float): Disposable
}