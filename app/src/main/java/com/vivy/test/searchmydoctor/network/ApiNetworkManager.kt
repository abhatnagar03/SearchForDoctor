package com.vivy.test.searchmydoctor.network

import com.squareup.okhttp.OkHttpClient
import com.vivy.test.searchmydoctor.model.LoginToken
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Retrofit

class ApiNetworkManager(private val okHttpClient : OkHttpClient, private val baseUrl: String) : NetworkManager {

    private fun getLoginAdapter() : Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
    }

    override fun login(username: String, password: String, serverTokenResponse: Callback<LoginToken>) {
        getLoginAdapter()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
                .login("password", username, password)
                .enqueue(serverTokenResponse)
    }
}