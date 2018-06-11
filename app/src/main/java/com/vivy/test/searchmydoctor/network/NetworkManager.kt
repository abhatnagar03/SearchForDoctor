package com.vivy.test.searchmydoctor.network

import io.reactivex.disposables.Disposable

interface NetworkManager {
    fun login(username: String, password: String): Disposable
    fun searchDoctorByName(docName: String, latitude: Double, longitude: Double): Disposable
    fun searchAllDoctors(latitude: Double, longitude: Double): Disposable
}