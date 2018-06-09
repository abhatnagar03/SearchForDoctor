package com.vivy.test.searchmydoctor.presenter

import android.content.Context
import android.text.Editable
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.contract.LoginContract
import java.util.concurrent.TimeUnit

/**
 * Created by tae-hwan on 2/17/17.
 */

class LoginPresenter : LoginContract.Presenter, AbstractPresenter<LoginContract.View>() {

    override lateinit var context: Context
//    override lateinit var loginRepository: LoginRepository

    init {
        initLogin()
    }

    private fun initLogin() {
//        loginDisposable = loginSubject
//                .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
//                .subscribe(
//                        { validLogin(it.email, it.password) },
//                        { view?.failLoginError(context.getString(R.string.fail_login)) }
//                )
    }

    private fun validLogin(email: Editable, password: Editable) {
        if (email.isNullOrEmpty()) {
            view?.updateEmailError(context.getString(R.string.error_field_required))
            return
        }

        if (!isPasswordValid(password.toString())) {
            view?.updatePasswordError(context.getString(R.string.error_invalid_password))
            return
        }

        if (!isEmailValid(email.toString())) {
            view?.updateEmailError(context.getString(R.string.error_invalid_email))
            return
        }

        view?.showProgress()

//        loginRepository.dumpLogin(User(email.toString(), password.toString()))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .filter {
//                    if (!it) {
//                        view?.failLoginError(context.getString(R.string.error_field_required))
//                        return@filter false
//                    }
//                    it
//                }
//                .doOnComplete {
//                    view?.hideProgress()
//                }
//                .subscribe {
//                    view?.successLogin(email.toString())
//                }
    }

    override fun loginUser(email: Editable, password: Editable) {
        //loginSubject.onNext(TempData(email, password))
    }

    private fun isEmailValid(email: String?)
            = email?.contains("@") ?: false

    private fun isPasswordValid(password: String?)
            = !password.isNullOrEmpty() || password?.let { it.length > 3 } ?: false

    override fun logout() {
        // 세션 처리
        view?.successLogout()
    }

    data class TempData(val email: Editable, val password: Editable)
}