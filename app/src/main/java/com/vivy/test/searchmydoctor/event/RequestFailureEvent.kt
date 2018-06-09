package com.vivy.test.searchmydoctor.event

class RequestFailureEvent (private val failureReason: String) {

    override fun toString(): String {
        return "RequestFailureEvent(failureReason='$failureReason')"
    }
}