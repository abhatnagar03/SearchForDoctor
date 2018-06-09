package com.vivy.test.searchmydoctor.presenter

import com.vivy.test.searchmydoctor.view.BaseView

interface BasePresenter<in VIEW : BaseView> {

    /**
     * View Attach.
     */
    fun attachView(view: VIEW)

    /**
     * View detach
     */
    fun detachView()
}