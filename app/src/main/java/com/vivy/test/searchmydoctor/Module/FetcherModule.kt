package com.vivy.test.searchmydoctor.Module

import com.vivy.test.searchmydoctor.Module.ApplicationModule.applicationContext
import com.vivy.test.searchmydoctor.Utils.StringEncryptorImpl
import com.vivy.test.searchmydoctor.fetcher.LoginFetcher
import com.vivy.test.searchmydoctor.fetcher.LoginNetworkFetcher
import com.vivy.test.searchmydoctor.fetcher.SearchFetcher
import com.vivy.test.searchmydoctor.fetcher.SearchNetworkFetcher
import com.vivy.test.searchmydoctor.network.NetworkManagerModule.Companion.apiNetworkManager
import com.vivy.test.searchmydoctor.repository.PreferenceRepo
import com.vivy.test.searchmydoctor.repository.PreferenceRepoImpl
import com.vivy.test.searchmydoctor.repository.PreferenceTokenRepository
import com.vivy.test.searchmydoctor.repository.TokenRepository

class FetcherModule {
    companion object {
        fun loginFetcher(): LoginFetcher {
            return LoginNetworkFetcher(apiNetworkManager())
        }

        fun searchFetcher(): SearchFetcher {
            return SearchNetworkFetcher(apiNetworkManager())
        }

        fun prefRepo() : PreferenceRepo {
            return PreferenceRepoImpl(applicationContext())
        }

        fun tokenRepository() : TokenRepository {
            return PreferenceTokenRepository(StringEncryptorImpl(), prefRepo())
        }
    }
}