package com.vivy.test.searchmydoctor.fetcher

import com.vivy.test.searchmydoctor.network.NetworkManager
import io.reactivex.disposables.Disposable

class SearchNetworkFetcher(private val networkManager: NetworkManager)
    : SearchFetcher{

    override fun searchAllDoctors(lat: Double?, lng: Double?): Disposable {
        return networkManager.searchAllDoctors(lat as Double, lng as Double)
    }

    override fun searchDoctorByName(docName: String, lat: Double, longi: Double): Disposable {
        return networkManager.searchDoctorByName(docName, lat, longi)
    }
}
