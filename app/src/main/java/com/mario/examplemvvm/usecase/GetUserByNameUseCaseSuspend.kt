package com.mario.examplemvvm.usecase

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.service.ServiceUser
import com.mario.examplemvvm.usecase.common.UseCaseSuspendWithParams

class GetUserByNameUseCaseSuspend(
    private val serviceUser: ServiceUser
) : UseCaseSuspendWithParams<Either<String, UserData?>, String> {

    override suspend fun execute(params: String): Either<String, UserData?> = serviceUser.getUserById(params)
}