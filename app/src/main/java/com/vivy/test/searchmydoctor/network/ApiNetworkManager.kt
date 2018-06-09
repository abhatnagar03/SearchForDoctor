package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.event.LoginTokenEvent
import com.vivy.test.searchmydoctor.event.RequestFailureEvent
import com.vivy.test.searchmydoctor.event.SearchSuccessEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiNetworkManager(private val loginClient : OkHttpClient, private val searchClient: OkHttpClient,
                        private val baseUrl: String) : NetworkManager {

    private val loginApi by lazy {
        getLoginAdapter()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    private val searchApi by lazy {
        getSearchAdapter()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    private fun getLoginAdapter() : Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(loginClient)
    }

    private fun getSearchAdapter() : Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(searchClient)
    }

    override fun login(username: String, password: String): Disposable {
        return loginApi
                .login("password", username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> RxBus.publish(LoginTokenEvent(result))},
                        { error -> RxBus.publish(RequestFailureEvent(error.localizedMessage)) }
                )
    }

    override fun searchDoctorByName(docName: String, latitude: Float, longitude: Float): Disposable {
        return searchApi
                .searchDoctorByName(docName, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> RxBus.publish(SearchSuccessEvent(result))},
                        { error -> RxBus.publish(RequestFailureEvent(error.localizedMessage)) }
                )
    }

    override fun searchAllDoctors(latitude: Float, longitude: Float): Disposable {
        return searchApi
                .searchAllDoctors(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> RxBus.publish(SearchSuccessEvent(result))},
                        { error -> RxBus.publish(RequestFailureEvent(error.localizedMessage)) }
                )
    }
}