package com.vivy.test.searchmydoctor.contract

import android.content.Context
import android.text.Editable
import com.vivy.test.searchmydoctor.presenter.BasePresenter
import com.vivy.test.searchmydoctor.view.BaseView

interface LoginContract {

    interface View : BaseView {

        fun showProgress()

        fun hideProgress()
        fun updatePasswordError(string: String)
        fun updateEmailError(string: String)
        fun failLoginError(string: String)
        fun successLogin(email: String)
        fun successLogout()
    }

    interface Presenter : BasePresenter<View> {

        var context: Context
        //var loginRepository: LoginRepository

        fun loginUser(email: Editable, password: Editable)

        fun logout()
    }
}