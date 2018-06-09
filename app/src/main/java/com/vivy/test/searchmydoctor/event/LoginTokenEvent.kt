package com.vivy.test.searchmydoctor.event

import com.vivy.test.searchmydoctor.model.LoginToken
import java.io.Serializable

class LoginTokenEvent(private val token: LoginToken) : Serializable {

    fun getToken(): LoginToken {
        return token
    }
}