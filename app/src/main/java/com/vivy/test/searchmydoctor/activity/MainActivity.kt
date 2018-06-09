package com.vivy.test.searchmydoctor.activity

import android.os.Bundle
import android.widget.Toast
import com.vivy.test.searchmydoctor.Module.ApplicationModule
import com.vivy.test.searchmydoctor.Module.FetcherModule.Companion.loginFetcher
import com.vivy.test.searchmydoctor.R
import com.vivy.test.searchmydoctor.Utils.FileUtils
import com.vivy.test.searchmydoctor.event.LoginFailureEvent
import com.vivy.test.searchmydoctor.event.LoginTokenEvent
import com.vivy.test.searchmydoctor.eventbus.RxBus
import com.vivy.test.searchmydoctor.fetcher.LoginFetcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var mLoginFetcher: LoginFetcher = loginFetcher()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val credentials: Array<String>
        val authString = FileUtils.createStringFromInputStream(ApplicationModule.getResources().openRawResource(R.raw.user_credentials))
        credentials = authString.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val userId = credentials[0]
        val pass = credentials[1]

        email.setText(userId)
        password.setText(pass)
        sign_in_btn.setOnClickListener {
            mLoginFetcher.login(email.text.toString(), password.text.toString())
        }

        RxBus.listen(LoginTokenEvent::class.java).subscribe({
            Toast.makeText(this, it.getToken().accessToken.toString(), Toast.LENGTH_LONG).show();
        })

        RxBus.listen(LoginFailureEvent::class.java).subscribe({
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show();
        })
    }
}
