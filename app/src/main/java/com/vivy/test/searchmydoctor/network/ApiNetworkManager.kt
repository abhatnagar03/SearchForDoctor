package com.vivy.test.searchmydoctor.network

import com.squareup.okhttp.OkHttpClient
import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.model.LoginToken
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Retrofit

class ApiNetworkManager(private val loginClient : OkHttpClient, private val searchClient: OkHttpClient,
                        private val baseUrl: String) : NetworkManager {

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

    override fun login(username: String, password: String, serverTokenResponse: Callback<LoginToken>) {
        getLoginAdapter()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
                .login("password", username, password)
                .enqueue(serverTokenResponse)
    }

    override fun searchDoctorByName(docName: String, latitude: Float, longitude: Float, searchResponse: Callback<DoctorsList>) {
        getSearchAdapter()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
                .searchDoctorByName(docName, latitude, longitude)
                .enqueue(searchResponse)
    }

    override fun searchAllDoctors(latitude: Float, longitude: Float, searchResponse: Callback<DoctorsList>) {
        getSearchAdapter()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
                .searchAllDoctors(latitude, longitude)
                .enqueue(searchResponse)
    }
}