package com.mario.examplemvvm.koin

import com.mario.examplemvvm.usecase.GetUserByNameUseCaseSuspend
import com.mario.examplemvvm.usecase.GetUserListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetUserListUseCase(get()) }

    factory { GetUserByNameUseCaseSuspend(get()) }
}