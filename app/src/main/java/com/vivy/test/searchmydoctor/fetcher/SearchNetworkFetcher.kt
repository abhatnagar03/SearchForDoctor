package com.vivy.test.searchmydoctor.fetcher

import com.vivy.test.searchmydoctor.event.RequestFailureEvent
import com.vivy.test.searchmydoctor.event.SearchSuccessEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.network.NetworkManager

import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class SearchNetworkFetcher(private val networkManager: NetworkManager)
    : SearchFetcher, Callback<DoctorsList> {

    override fun searchAllDoctors(lat: Float?, lng: Float?) {
        networkManager.searchAllDoctors(lat as Float, lng as Float, this)
    }

    override fun searchDoctorByName(docName: String, lat: Float, longi: Float) {
        networkManager.searchDoctorByName(docName, lat, longi, this)
    }


    override fun onResponse(response: Response<DoctorsList>, retrofit: Retrofit) {
        if (null != response.body() && response.isSuccess)
            RxBus.publish(SearchSuccessEvent(response.body()))
        else
            RxBus.publish(RequestFailureEvent("Some error occurred"))
    }

    override fun onFailure(t: Throwable) {
        RxBus.publish(RequestFailureEvent(t.localizedMessage))
    }
}
