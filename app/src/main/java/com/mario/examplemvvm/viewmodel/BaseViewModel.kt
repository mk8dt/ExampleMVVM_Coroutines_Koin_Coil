package com.mario.examplemvvm.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ViewModelView> : ViewModel() {

    protected var view: T? = null

    fun init(viewModelView: T) {
        view = viewModelView
        onCreate()
    }

    protected abstract fun onCreate()

    abstract fun onDestroy()
}