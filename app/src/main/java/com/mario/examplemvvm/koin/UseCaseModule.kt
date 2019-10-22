package com.mario.examplemvvm.koin

import com.mario.examplemvvm.usecase.GetUserListUseCase
import com.mario.examplemvvm.usecase.GetUserUseCase
import com.mario.examplemvvm.usecase.SaveUserIdUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetUserListUseCase(get()) }

    single { GetUserUseCase(get()) }

    single { SaveUserIdUseCase(get()) }
}