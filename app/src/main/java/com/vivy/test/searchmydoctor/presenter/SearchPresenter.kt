package com.vivy.test.searchmydoctor.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.adapter.AdapterExample
import com.vivy.test.searchmydoctor.contract.SearchContract
import com.vivy.test.searchmydoctor.event.RequestFailureEvent
import com.vivy.test.searchmydoctor.event.SearchSuccessEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher
import com.vivy.test.searchmydoctor.locationManager.CurrentLatLong
import com.vivy.test.searchmydoctor.locationManager.LocationCallbackListener
import com.vivy.test.searchmydoctor.model.Doctor
import com.vivy.test.searchmydoctor.repository.TokenRepository
import io.reactivex.disposables.Disposable

class SearchPresenter() : SearchContract.Presenter, AbstractPresenter<SearchContract.View>() {

    override lateinit var context: Context
    override lateinit var searchFetcher: SearchFetcher
    override lateinit var loginRepo : TokenRepository

    private var disposable: Disposable? = null
    init {
        initSearch()
    }

    private fun initSearch() {
        RxBus.listen(SearchSuccessEvent::class.java).subscribe({
            view?.hideProgress()
            view?.initializeList(it.getDoctors())
        })

        RxBus.listen(RequestFailureEvent::class.java).subscribe({
            view?.hideProgress()
            view?.showFailureError(it.toString())
            logout()
        })
    }

    override fun logout() {
        loginRepo.clearRepo()
        view?.finishActivity()
    }

    override fun searchDoctorByText(searchText: String, lat: Double, long: Double) {
        view?.showProgress()
        disposable = searchFetcher.searchDoctorByText(searchText, lat, long)
    }

    override fun getAllDoctors(lat: Double, long: Double) {
        view?.showProgress()
        disposable = searchFetcher.searchAllDoctors(lat, long)
    }

    override fun fetchLocation(context: Context, listener: LocationCallbackListener) {
        CurrentLatLong(context, listener)
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false)
    }

    override fun getAdapter(items: List<*>): RecyclerView.Adapter<*> {
        return AdapterExample(context, items as ArrayList<Doctor>, R.layout.item_type_main)
    }

    override fun activityPaused() {
        disposable?.dispose()
    }
}