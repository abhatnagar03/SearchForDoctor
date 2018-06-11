package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.model.DoctorsList
import com.vivy.test.searchmydoctor.model.LoginToken
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/x-www-form-urlencoded", "Accept: application/json")
    @POST("oauth/token")
    fun login(@Query("grant_type") key: String,
              @Query("username") userName: String,
              @Query("password") password: String): Observable<LoginToken>

    @Headers("Accept: application/json")
    @GET("api/users/me/doctors")
    fun searchDoctorByName(@Query("search") name: String,
                           @Query("lat") lat: Double,
                           @Query("lng") lng: Double): Observable<DoctorsList>

    @Headers("Accept: application/json")
    @GET("api/users/me/doctors")
    fun searchAllDoctors(@Query("lat") lat: Double,
                         @Query("lng") lng: Double): Observable<DoctorsList>

    @GET("api/users/me/files")
    fun getPhoto(@Path("fileId") path: String): Observable<Url>
}