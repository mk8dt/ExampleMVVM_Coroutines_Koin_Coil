package com.mario.examplemvvm.usecase.common

interface UseCaseSuspend<T> {

    suspend fun execute(): T
}