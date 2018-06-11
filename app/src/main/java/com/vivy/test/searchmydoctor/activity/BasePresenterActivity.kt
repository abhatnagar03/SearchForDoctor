package com.vivy.test.searchmydoctor.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vivy.test.searchmydoctor.presenter.BasePresenter
import com.vivy.test.searchmydoctor.view.BaseView

/**
 * Base activity with view and presenter
 */
abstract class BasePresenterActivity<in VIEW: BaseView, P : BasePresenter<VIEW>> : AppCompatActivity() {

    protected var presenter: P? = null
        private set

    abstract fun onCreatePresenter(): P?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = onCreatePresenter()
        presenter?.attachView(this as VIEW)
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter?.detachView()
    }
}