package com.vivy.test.searchmydoctor.presenter

import android.content.Context
import android.text.Editable
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.contract.LoginContract
import com.vivy.test.searchmydoctor.event.RequestFailureEvent
import com.vivy.test.searchmydoctor.event.LoginTokenEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import com.vivy.test.searchmydoctor.fetcher.LoginFetcher
import com.vivy.test.searchmydoctor.repository.TokenRepository
import io.reactivex.disposables.Disposable

class LoginPresenter() : LoginContract.Presenter, AbstractPresenter<LoginContract.View>() {

    override lateinit var context: Context
    override lateinit var loginFetcher: LoginFetcher
    override lateinit var loginRepo: TokenRepository

    private var disposable: Disposable? = null

    init {
        initLogin()
    }

    private fun initLogin() {
        RxBus.listen(LoginTokenEvent::class.java).subscribe({
            view?.hideProgress()
            view?.successLogin()
            loginRepo.setAccessToken(it.getToken())
            loginRepo.setRefreshToken(it.getToken())
        })

        RxBus.listen(RequestFailureEvent::class.java).subscribe({
            view?.hideProgress()
            view?.failLoginError(it.toString())
        })
    }

    private fun validateLogin(email: Editable, password: Editable): Boolean {
        if (email.isEmpty()) {
            view?.updateEmailError(context.getString(R.string.error_field_required))
            return false
        }

        if (password.isEmpty()) {
            view?.updatePasswordError(context.getString(R.string.error_field_required))
            return false
        }

        if (!isPasswordValid(password.toString())) {
            view?.updatePasswordError(context.getString(R.string.error_invalid_password))
            return false
        }

        if (!isEmailValid(email.toString())) {
            view?.updateEmailError(context.getString(R.string.error_invalid_email))
            return false
        }

        view?.showProgress()
        return true
    }

    private fun isEmailValid(email: String?) = email?.contains("@") ?: false

    private fun isPasswordValid(password: String?) = !password.isNullOrEmpty() || password?.let { it.length > 3 } ?: false

    override fun loginUser(email: Editable, password: Editable) {
        if (validateLogin(email, password))
            disposable = loginFetcher.login(email.toString(), password.toString())
    }

    override fun logout() {
        view?.successLogout()
    }

    override fun activityPaused() {
        disposable?.dispose()
        view?.hideProgress()
    }
    override fun activityResumed() {
        view?.showProgress()
        if(!loginRepo.getAccessToken().isEmpty()) {
            view?.successLogin()
        } else {
            view?.hideProgress()
        }
    }
}