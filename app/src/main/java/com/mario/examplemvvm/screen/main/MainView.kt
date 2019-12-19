package com.mario.examplemvvm.screen.main

import com.mario.examplemvvm.screen.base.ViewModelView

interface MainView : ViewModelView {

    fun showLoading()

    fun hideLoading()
}