package com.mario.examplemvvm.usecase

import com.mario.core.domain.Either
import com.mario.core.domain.data.UserData
import com.mario.core.domain.provider.ProviderUser
import com.mario.examplemvvm.usecase.common.UseCaseSuspend

class GetUserListUseCase(
    private val providerUser: ProviderUser
) : UseCaseSuspend<Either<String, List<UserData>>> {

    override suspend fun execute(): Either<String, List<UserData>> {
        return providerUser.getUserList()
    }
}