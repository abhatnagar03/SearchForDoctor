package com.vivy.test.searchmydoctor.fetcher

import com.vivy.test.searchmydoctor.network.NetworkManager
import io.reactivex.disposables.Disposable

class LoginNetworkFetcher(private val networkManager: NetworkManager)
    : LoginFetcher {
    override fun login(username: String, password: String): Disposable {
        return networkManager.login(username, password)
    }
}
