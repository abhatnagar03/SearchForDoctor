package com.vivy.test.searchmydoctor.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.vivy.test.searchmydoctor.Module.ApplicationModule
import com.vivy.test.searchmydoctor.Module.FetcherModule.Companion.loginFetcher
import com.vivy.test.searchmydoctor.Module.FetcherModule.Companion.tokenRepository
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.Utils.FileUtils
import com.vivy.test.searchmydoctor.contract.LoginContract
import com.vivy.test.searchmydoctor.fetcher.LoginFetcher
import com.vivy.test.searchmydoctor.presenter.LoginPresenter
import com.vivy.test.searchmydoctor.repository.TokenRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BasePresenterActivity<LoginContract.View, LoginContract.Presenter>(),
        LoginContract.View {
    override fun onCreatePresenter() = LoginPresenter()

    private var mLoginFetcher: LoginFetcher = loginFetcher()
    private var mTokenRepository: TokenRepository = tokenRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter?.context = this
        presenter?.loginFetcher = mLoginFetcher
        presenter?.loginRepo = mTokenRepository

        val credentials: Array<String>
        val authString = FileUtils.createStringFromInputStream(ApplicationModule.getResources().openRawResource(R.raw.user_credentials))
        credentials = authString.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val userId = credentials[0]
        val pass = credentials[1]

        email.setText(userId)
        password.setText(pass)
        sign_in_btn.setOnClickListener {
            presenter?.loginUser(email = email.text, password = password.text)
        }
    }

    override fun showProgress() {
        progress_layout.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_layout.visibility = View.GONE
    }

    override fun updatePasswordError(string: String) {
        password.error = string
    }

    override fun updateEmailError(string: String) {
        email.error = string
    }

    override fun failLoginError(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun successLogin() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    override fun successLogout() {
        TODO("Out of scope")
    }

    override fun onPause() {
        super.onPause()
        presenter?.activityPaused()
    }

    override fun onResume() {
        super.onResume()
        presenter?.activityResumed()
    }
}