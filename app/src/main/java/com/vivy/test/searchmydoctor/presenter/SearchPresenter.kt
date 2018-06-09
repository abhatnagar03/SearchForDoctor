package com.vivy.test.searchmydoctor.presenter

import android.content.Context
import com.vivy.test.searchmydoctor.contract.SearchContract
import com.vivy.test.searchmydoctor.event.RequestFailureEvent
import com.vivy.test.searchmydoctor.event.SearchSuccessEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher

class SearchPresenter() : SearchContract.Presenter, AbstractPresenter<SearchContract.View>() {

    override lateinit var context: Context
    override lateinit var searchFetcher: SearchFetcher

    init {
        initSearch()
    }

    private fun initSearch() {
        RxBus.listen(SearchSuccessEvent::class.java).subscribe({
            //view?.hideProgress()
            view?.initializeList(it.getDoctors())
        })

        RxBus.listen(RequestFailureEvent::class.java).subscribe({
            view?.hideProgress()
            view?.showFailureError(it.toString())
        })
    }

    override fun searchDoctor(docName: String, lat: Float, long: Float) {
        searchFetcher.searchDoctorByName(docName, lat, long)
    }

    override fun getAllDoctors(lat: Float, long: Float) {
        searchFetcher.searchAllDoctors(52.534709f, 13.3976972f)
    }
}