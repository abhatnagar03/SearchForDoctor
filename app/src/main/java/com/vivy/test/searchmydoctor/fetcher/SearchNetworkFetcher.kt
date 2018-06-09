package com.vivy.test.searchmydoctor.fetcher

import com.vivy.test.searchmydoctor.network.NetworkManager
import io.reactivex.disposables.Disposable

class SearchNetworkFetcher(private val networkManager: NetworkManager)
    : SearchFetcher{

    override fun searchAllDoctors(lat: Float?, lng: Float?): Disposable {
        return networkManager.searchAllDoctors(lat as Float, lng as Float)
    }

    override fun searchDoctorByName(docName: String, lat: Float, longi: Float): Disposable {
        return networkManager.searchDoctorByName(docName, lat, longi)
    }
}
