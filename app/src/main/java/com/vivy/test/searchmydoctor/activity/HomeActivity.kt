package com.vivy.test.searchmydoctor.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.vivy.test.searchmydoctor.Module.FetcherModule
import com.vivy.test.searchmydoctor.Module.FetcherModule.Companion.tokenRepository
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.Utils.Constants
import com.vivy.test.searchmydoctor.adapter.AdapterExample
import com.vivy.test.searchmydoctor.contract.SearchContract
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher
import com.vivy.test.searchmydoctor.locationManager.LocationCallbackListener
import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.presenter.SearchPresenter
import com.vivy.test.searchmydoctor.widget.ItemOffsetDecoration
import kotlinx.android.synthetic.main.activity_home.*

open class HomeActivity() : BasePresenterActivity<SearchContract.View, SearchContract.Presenter>(),
        SearchContract.View, LocationCallbackListener {

    var longitude: Double = 0.00
    var latitude: Double = 0.00

    override fun onCreatePresenter() = SearchPresenter()

    private var mSearchFetcher: SearchFetcher = FetcherModule.searchFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(mainToolbar)

        presenter?.context = this
        presenter?.searchFetcher = mSearchFetcher
        presenter?.loginRepo = tokenRepository()

        setupRecyclerView()
    }

    override fun showProgress() {
        progress_layout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_layout.visibility = View.GONE
    }

    override fun updateList(doctors: DoctorsList) {
    }

    override fun initializeList(doctors: DoctorsList) {
        Toast.makeText(this, " " + doctors.doctorsList.size, Toast.LENGTH_SHORT).show()
        recycler_view.adapter = AdapterExample(this, doctors.doctorsList as ArrayList, R.layout.item_type_main)
    }

    override fun showFailureError(string: String) {
    }

    private fun setupRecyclerView() {
        recycler_view.setLayoutManager(presenter?.getLayoutManager())

        recycler_view.addItemDecoration(ItemOffsetDecoration(recycler_view.getContext(), R.dimen.item_decoration))
        recycler_view.setItemAnimator(DefaultItemAnimator())
    }

    override fun onResume() {
        super.onResume()
        presenter?.fetchLocation(this, this)
    }

    override fun onPause() {
        super.onPause()
        presenter?.activityPaused()
    }

    override fun onLocationRecieved(lat: Double, lng: Double) {
        latitude = lat
        longitude = lng
        presenter?.getAllDoctors(lat, lng)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Constants.MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        presenter?.fetchLocation(this, this)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.enable_settings), Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        val searchItem: MenuItem = menu?.findItem(R.id.action_search) as MenuItem;
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                supportActionBar?.title = query
                searchView.setQuery("", false)
                searchView.clearFocus()
                // Fetch the data remotely
                presenter?.searchDoctorByText(query, latitude,
                        longitude)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener {
            supportActionBar?.title = getString(R.string.app_name)
            presenter?.getAllDoctors(latitude, longitude)
            false
        }
        return true
    }
}
