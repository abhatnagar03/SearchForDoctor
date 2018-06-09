package com.vivy.test.searchmydoctor.event

class LoginFailureEvent (private val failureReason: String) {

    override fun toString(): String {
        return "LoginFailureEvent(failureReason='$failureReason')"
    }
}