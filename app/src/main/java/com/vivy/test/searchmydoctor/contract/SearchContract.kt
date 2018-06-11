package com.vivy.test.searchmydoctor.contract

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher
import com.vivy.test.searchmydoctor.locationManager.LocationCallbackListener
import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.presenter.BasePresenter
import com.vivy.test.searchmydoctor.repository.TokenRepository
import com.vivy.test.searchmydoctor.view.BaseView

interface SearchContract {

    interface View : BaseView {

        fun showProgress()
        fun hideProgress()
        fun updateList(doctors: DoctorsList)
        fun initializeList(doctors: DoctorsList)
        fun showFailureError(string: String)
    }

    interface Presenter : BasePresenter<View> {

        var context: Context
        var searchFetcher: SearchFetcher
        var loginRepo: TokenRepository

        fun searchDoctorByText(searchText: String, lat: Double, long: Double)
        fun getAllDoctors(lat: Double, long: Double)
        fun fetchLocation(context: Context, listener: LocationCallbackListener)
        fun getLayoutManager(): RecyclerView.LayoutManager
        fun getAdapter(items: List<*>): RecyclerView.Adapter<*>
        fun activityPaused()
    }
}