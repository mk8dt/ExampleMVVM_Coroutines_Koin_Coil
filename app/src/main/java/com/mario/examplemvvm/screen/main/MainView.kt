package com.mario.examplemvvm.screen.main

import com.mario.examplemvvm.viewmodel.ViewModelView

interface MainView : ViewModelView {

    fun showLoading()

    fun hideLoading()

    fun showToast(message:String)
}