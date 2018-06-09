package com.vivy.test.searchmydoctor.Module

import com.vivy.test.searchmydoctor.fetcher.LoginFetcher
import com.vivy.test.searchmydoctor.fetcher.LoginNetworkFetcher
import com.vivy.test.searchmydoctor.network.NetworkManagerModule.Companion.apiNetworkManager

class FetcherModule {
    companion object {
        fun loginFetcher(): LoginFetcher {
            return LoginNetworkFetcher(apiNetworkManager())
        }
    }
}