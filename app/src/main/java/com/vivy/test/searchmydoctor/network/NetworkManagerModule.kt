package com.vivy.test.searchmydoctor.network

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import com.vivy.test.searchmydoctor.Module.ApplicationModule.getResources
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.Utils.FileUtils
import java.util.concurrent.TimeUnit

class NetworkManagerModule {
    companion object {
        private val AS_TIMEOUT = 60
        private var sGRSClient: OkHttpClient? = null

        private fun okGRSClient(): OkHttpClient {
            if (sGRSClient == null) {
                sGRSClient = OkHttpClient()
                sGRSClient!!.setRetryOnConnectionFailure(true)
                sGRSClient!!.setReadTimeout(AS_TIMEOUT.toLong(), TimeUnit.SECONDS)
                sGRSClient!!.setConnectTimeout(AS_TIMEOUT.toLong(), TimeUnit.SECONDS)

                // Adding interceptor to inject basic authorisation
                sGRSClient!!.interceptors().add(basicAuthorizationRequestInterceptor())
            }

            return sGRSClient as OkHttpClient
        }

        private fun basicAuthorizationRequestInterceptor(): Interceptor {
            val authToken = FileUtils.createStringFromInputStream(getResources().openRawResource(R.raw.client_credentials))
            return AuthenticationInterceptor(authToken)
        }

        fun apiNetworkManager(): NetworkManager {
            return ApiNetworkManager(okGRSClient(), getResources().getString(R.string.base_url))
        }
    }
}