package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.model.LoginToken
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Headers
import retrofit.http.POST
import retrofit.http.Query

interface ApiService {
    @Headers("Content-Type: application/x-www-form-urlencoded", "Accept: application/json")
    @POST("oauth/token")
    fun login(@Query("grant_type") key: String,
                            @Query("username") userName: String,
                            @Query("password") password: String): Call<LoginToken>

    @Headers("Accept: application/json")
    @POST("api/users/me/doctors")
    fun searchDoctorByName(@Query("search") name: String,
                           @Query("lat") lat: Float,
                           @Query("lng") lng: Float): Call<DoctorsList>

    @Headers("Accept: application/json")
    @GET("api/users/me/doctors")
    fun searchAllDoctors(@Query("lat") lat: Float,
                         @Query("lng") lng: Float): Call<DoctorsList>
}