package com.mario.examplemvvm

import android.app.Application
import com.mario.examplemvvm.koin.appModule
import com.mario.examplemvvm.koin.domainModule
import com.mario.examplemvvm.koin.useCaseModule
import com.mario.examplemvvm.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ExampleMVVM : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ExampleMVVM)
            androidLogger()
            loadKoinModules(listOf(appModule, useCaseModule, domainModule, viewModelModule))
        }
    }
}