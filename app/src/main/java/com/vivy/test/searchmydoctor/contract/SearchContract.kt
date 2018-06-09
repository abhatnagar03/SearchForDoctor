package com.vivy.test.searchmydoctor.contract

import android.content.Context
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher
import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.presenter.BasePresenter
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

        fun searchDoctor(docName: String, lat: Float, long: Float)
        fun getAllDoctors(lat: Float, long: Float)
    }
}