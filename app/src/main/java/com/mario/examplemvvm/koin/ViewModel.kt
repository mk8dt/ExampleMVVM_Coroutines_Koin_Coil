package com.mario.examplemvvm.koin

import com.mario.examplemvvm.screen.detail.DetailViewModel
import com.mario.examplemvvm.screen.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}