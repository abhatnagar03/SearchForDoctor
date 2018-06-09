package com.vivy.test.searchmydoctor.fetcher

import com.vivy.test.searchmydoctor.event.RequestFailureEvent
import com.vivy.test.searchmydoctor.event.LoginTokenEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import com.vivy.test.searchmydoctor.model.LoginToken
import com.vivy.test.searchmydoctor.network.NetworkManager

import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class LoginNetworkFetcher(private val networkManager: NetworkManager)
    : LoginFetcher, Callback<LoginToken> {
    override fun login(username: String, password: String) {
        networkManager.login(username, password, this)
    }

    override fun onResponse(response: Response<LoginToken>, retrofit: Retrofit) {
        if (null != response.body() && response.isSuccess)
            RxBus.publish(LoginTokenEvent(response.body()))
        else
           RxBus.publish(RequestFailureEvent("Some error occurred"))
    }

    override fun onFailure(t: Throwable) {
        RxBus.publish(RequestFailureEvent(t.localizedMessage))
    }
}
