package com.mario.examplemvvm.screen.main

import com.mario.examplemvvm.screen.base.BaseView

interface MainView : BaseView {

    fun showLoading()

    fun hideLoading()
}