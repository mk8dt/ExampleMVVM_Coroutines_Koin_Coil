package com.mario.examplemvvm.koin

import com.mario.examplemvvm.usecase.GetUserListUseCase
import com.mario.examplemvvm.usecase.GetUserByNameUseCaseSuspend
import org.koin.dsl.module

val useCaseModule = module {

    single { GetUserListUseCase(get()) }

    single { GetUserByNameUseCaseSuspend(get()) }
}