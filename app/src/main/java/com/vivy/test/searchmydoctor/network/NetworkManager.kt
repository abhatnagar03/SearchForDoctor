package com.vivy.test.searchmydoctor.network

import com.vivy.test.searchmydoctor.model.LoginToken
import retrofit.Callback

interface NetworkManager {
    fun login(username: String, password: String, serverTokenResponse: Callback<LoginToken>)
}