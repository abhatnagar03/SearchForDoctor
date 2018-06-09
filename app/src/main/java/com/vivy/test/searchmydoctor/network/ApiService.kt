package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.model.LoginToken
import retrofit.Call
import retrofit.http.Headers
import retrofit.http.POST
import retrofit.http.Query

interface ApiService {
    @Headers("Content-Type: application/x-www-form-urlencoded", "Accept: application/json")
    @POST("oauth/token")
    fun login(@Query("grant_type") key: String,
                            @Query("username") userName: String,
                            @Query("password") password: String): Call<LoginToken>
}