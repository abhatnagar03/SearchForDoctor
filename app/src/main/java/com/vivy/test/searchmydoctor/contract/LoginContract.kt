package com.vivy.test.searchmydoctor.contract

import android.content.Context
import android.text.Editable
import com.vivy.test.searchmydoctor.fetcher.LoginFetcher
import com.vivy.test.searchmydoctor.presenter.BasePresenter
import com.vivy.test.searchmydoctor.repository.TokenRepository
import com.vivy.test.searchmydoctor.view.BaseView

/**
 * Login contract binding login view and login presenter
 */
interface LoginContract {

    interface View : BaseView {

        fun showProgress()
        fun hideProgress()
        fun updatePasswordError(string: String)
        fun updateEmailError(string: String)
        fun failLoginError(string: String)
        fun successLogin()
        fun successLogout()
    }

    interface Presenter : BasePresenter<View> {

        var context: Context
        var loginFetcher: LoginFetcher
        var loginRepo: TokenRepository

        fun loginUser(email: Editable, password: Editable)
        fun logout()
        fun activityPaused()
        fun activityResumed()
    }
}