package com.vivy.test.searchmydoctor.network

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import com.vivy.test.searchmydoctor.Module.ApplicationModule.getResources
import com.vivy.test.searchmydoctor.Module.FetcherModule.Companion.tokenRepository
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.Utils.FileUtils
import java.util.concurrent.TimeUnit

class NetworkManagerModule {
    companion object {
        private val AS_TIMEOUT = 60
        private var loginClient: OkHttpClient? = null
        private var searchClient: OkHttpClient? = null

        private fun loginClient(): OkHttpClient {
            if (loginClient == null) {
                loginClient = OkHttpClient()
                loginClient!!.setRetryOnConnectionFailure(true)
                loginClient!!.setReadTimeout(AS_TIMEOUT.toLong(), TimeUnit.SECONDS)
                loginClient!!.setConnectTimeout(AS_TIMEOUT.toLong(), TimeUnit.SECONDS)

                // Adding interceptor to inject basic authorisation
                loginClient!!.interceptors().add(basicAuthorizationRequestInterceptor())
            }

            return loginClient as OkHttpClient
        }

        private fun searchClient(): OkHttpClient{
            if (searchClient == null) {
                searchClient = OkHttpClient()
                searchClient!!.setRetryOnConnectionFailure(true)
                searchClient!!.setReadTimeout(AS_TIMEOUT.toLong(), TimeUnit.SECONDS)
                searchClient!!.setConnectTimeout(AS_TIMEOUT.toLong(), TimeUnit.SECONDS)

                // Adding interceptor to inject basic authorisation
                searchClient!!.interceptors().add(bearerAuthorizationRequestInterceptor())
            }

            return searchClient as OkHttpClient
        }

        private fun basicAuthorizationRequestInterceptor(): Interceptor {
            val authToken = FileUtils.createStringFromInputStream(getResources().openRawResource(R.raw.client_credentials))
            return AuthenticationInterceptor("Basic", authToken)
        }

        private fun bearerAuthorizationRequestInterceptor(): Interceptor {
            val authToken = tokenRepository().getAccessToken()
            return AuthenticationInterceptor("Bearer", authToken)
        }

        fun apiNetworkManager(): NetworkManager {
            return ApiNetworkManager(loginClient(), searchClient(), getResources().getString(R.string.base_url))
        }
    }
}