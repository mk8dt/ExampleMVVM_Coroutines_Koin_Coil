package com.mario.examplemvvm.usecase.common

interface UseCaseSuspendWithParams<out T, in P> {

    suspend fun execute(params: P): T
}