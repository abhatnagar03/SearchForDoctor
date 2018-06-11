package com.vivy.test.searchmydoctor.repository

import com.vivy.test.searchmydoctor.model.LoginToken

interface TokenRepository {
    fun getAccessToken() : String
    fun setAccessToken(accessToken : LoginToken)
    fun getRefreshToken() : String
    fun setRefreshToken(refToken : LoginToken)
    fun clearRepo()
}