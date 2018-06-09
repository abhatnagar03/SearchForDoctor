package com.vivy.test.searchmydoctor.activity

import android.os.Bundle
import android.widget.Toast
import com.vivy.test.searchmydoctor.Module.FetcherModule
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.contract.SearchContract
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher
import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.presenter.SearchPresenter

open class HomeActivity() : BasePresenterActivity<SearchContract.View, SearchContract.Presenter>(), SearchContract.View {
    override fun onCreatePresenter() = SearchPresenter()

    private var mSearchFetcher: SearchFetcher = FetcherModule.searchFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter?.context = this
        presenter?.searchFetcher = mSearchFetcher
        presenter?.getAllDoctors(52.534709f, 13.3976972f)
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun updateList(doctors: DoctorsList) {
    }

    override fun initializeList(doctors: DoctorsList) {
        Toast.makeText(this, " " + doctors.doctorsList.size, Toast.LENGTH_SHORT).show()
    }

    override fun showFailureError(string: String) {
    }
}