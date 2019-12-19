package com.mario.examplemvvm.screen.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V : ViewModelView> : ViewModel() {

    protected var view: V? = null

    fun init(viewModelView: V) {
        view = viewModelView
        onCreate()
    }

    protected abstract fun onCreate()

    abstract fun onDestroy()
}