package com.mario.examplemvvm.usecase

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.provider.ProviderUser
import com.mario.examplemvvm.usecase.common.UseCaseSuspendWithParams

class GetUserByNameUseCaseSuspend(
    private val providerUser: ProviderUser
) : UseCaseSuspendWithParams<Either<String, UserData?>, String> {

    override suspend fun execute(params: String): Either<String, UserData?> = providerUser.getUserById(params)
}